package tbgt.web.controller;


import com.taobao.api.domain.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.*;
import tbgt.domain.Order;
import tbgt.freemarker.PendingSendingOrder;
import tbgt.service.BaoBeiService;
import tbgt.service.ExpressCodeService;
import tbgt.service.OrderService;
import tbgt.util.DateUtil;
import tbgt.web.criteria.OrderCriteria;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.web.paging.PagingEnabler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private BaoBeiService baoBeiService;
    private OrderService orderService;
    private ExpressCodeService expressCodeService;
    private Configuration freemarkerConfiguration;

    @Autowired
    public void setBaoBeiService(BaoBeiService baoBeiService) {
        this.baoBeiService = baoBeiService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setExpressCodeService(ExpressCodeService expressCodeService) {
        this.expressCodeService = expressCodeService;
    }

    @Autowired
    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewOrder() {
        ModelAndView mv = new ModelAndView("order");
        mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody PaginationTO list(@RequestParam String status) {
        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.setName( PagingContextHolder.get().getsSearch());
        orderCriteria.setStatus(status);
        List<Order> orders = orderService.getOrders(orderCriteria);
        return PagingEnabler.enablePaging(orders);
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public ModelAndView updateOrder(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("orderDetail");
        mv.addObject("order", orderService.getOrderById(id));
        return mv;
    }


    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public ModelAndView deleteOrder(@RequestParam long id) {
        ModelAndView mv = new ModelAndView("order");
        orderService.deleteOrder(id);
        return mv;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody String saveOrder(@ModelAttribute(value = "order") Order order, BindingResult result) {
        //todo..validator
        if (!result.hasErrors()) {
            if (order.getId() == 0) {
                orderService.saveOrder(order);
            } else {
                orderService.updateOrder(order);
            }
        }
        return "Success";
    }

    @RequestMapping(value = "/express", method = RequestMethod.GET)
    public ModelAndView express(@RequestParam long orderId) {
        ModelAndView mv = new ModelAndView("express");
        Express express = orderService.getExpressByOrderId(orderId);

        if(express ==null) {
            express = new Express();
            express.setOrderId(orderId);
        }
        mv.addObject("express", express);
        return mv;
    }

    @RequestMapping(value = "/saveExpress", method = RequestMethod.POST)
    public ModelAndView saveExpress(@ModelAttribute(value = "express") Express express, BindingResult result) {
        ModelAndView mv = new ModelAndView("order");
        //todo..validator
        if (!result.hasErrors()) {
            if (express.getId() == 0) {
                orderService.insertExpress(express);
            } else {
                orderService.updateExpress(express);
            }
        }
//        mv.addObject("orders", orderService.getAllOrders());
//        mv.addObject("orders", new ArrayList());
        mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @RequestMapping(value = "/getExpressFee", method = RequestMethod.POST)
    public @ResponseBody int getExpressFee(@RequestParam long orderId,@RequestParam String expressNo){

        Order order = orderService.getOrderById(orderId);
        BigDecimal weight = BigDecimal.ZERO;
        for(SoldBaobei soldBaobei : order.getSoldBaobeis()){
            String baobeiWeight = soldBaobei.getWeight();
            if(baobeiWeight==null) return 0;
            weight = weight.add(new BigDecimal(baobeiWeight));
        }
        String province = order.getReceiver_state();
        String type = "韵达";
        if(expressNo.startsWith("EF")){
            type = "E邮宝";
        }
        ExpressCode expressCode = expressCodeService.getExpressCode(province,type);
        if(expressCode==null) return 0;
        return expressCodeService.getExpressFee(expressCode,weight) ;
    }


    @RequestMapping(value = "/refreshOrder", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refreshOrder(@RequestParam long orderId,@RequestParam String status,@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        orderService.refreshOneOrder(top_session,orderId);
        mv.addObject("status",status);
        return mv;
    }

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView refresh(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        orderService.refreshOrder(top_session, false);
        mv.addObject("status", "WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @RequestMapping(value = "/refreshAll", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refreshAll(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        orderService.refreshOrder(top_session,true);
        mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @RequestMapping(value = "/send", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView send(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        boolean  result = orderService.send(top_session);
        if(!result) {
            mv.addObject("errormsg","发货失败");
            mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        }else{
            mv.addObject("status","WAIT_BUYER_CONFIRM_GOODS");
        }

        return mv;
    }

    @RequestMapping(value = "/viewExpressStatus", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView viewExpressStatus(@RequestParam long orderid) throws Exception {
        ModelAndView mv = new ModelAndView("expressStatus");
        List<TransitStepInfo> transitStepInfos = orderService.viewExpressStatus(orderid);
        mv.addObject("expSttsLst",transitStepInfos);
        return mv;
    }

    @RequestMapping(value = "/exportWordForPendingSendingOrder", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView exportWordForPendingSendingOrder(HttpServletRequest request,HttpServletResponse response) throws Exception{
        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.setStatus("WAIT_SELLER_SEND_GOODS");
        List<Order> orders = orderService.getOrders(orderCriteria);
        Template temp = freemarkerConfiguration.getTemplate("ftl/pending_send_list.ftl");
        temp.setEncoding("utf-8");

        List<PendingSendingOrder> pendingSendingOrders = new ArrayList<PendingSendingOrder>();
        int i = 0;
        for (Order order : orders) {
            PendingSendingOrder pendingSendingOrder = new PendingSendingOrder();
            pendingSendingOrder.setAddress(order.getReceiver_address());

            for (SoldBaobei soldBaobei : order.getSoldBaobeis()) {
                StringBuffer buf = new StringBuffer();
                buf.append(soldBaobei.getTitle()).append(",  ").append("数量 : ").append(soldBaobei.getQuantity())
                        .append(",  ").append(soldBaobei.getSku_properties_name());
                pendingSendingOrder.addDetail(buf.toString());
            }

            pendingSendingOrder.setMemo("");
            pendingSendingOrder.setBuyer_message(order.getBuyer_msg());
            pendingSendingOrder.setNo(String.valueOf((++i)));
            pendingSendingOrders.add(pendingSendingOrder);
        }
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("lst", pendingSendingOrders);
        String today = new DateTime().toString(DateUtil.DATE_FORMAT);
        root.put("pay_date", today);
        response.setContentType("application/x-msdownload;");
        String filenameWithoutext = "发货单"+today;
        String filename = new String(filenameWithoutext.getBytes("utf-8"), "ISO8859-1");
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filenameWithoutext, "UTF-8");//IE浏览器
        }
        response.setHeader("Content-disposition", "attachment; filename="+ filename +".doc");
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            temp.process(root, new BufferedWriter(new OutputStreamWriter(bos)));
        } finally {
            if (bos != null) bos.close();
        }
        return null;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ex.getMessage();
    }


}
