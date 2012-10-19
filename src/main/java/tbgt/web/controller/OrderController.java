package tbgt.web.controller;


import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.*;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.*;
import tbgt.domain.Order;
import tbgt.service.BaoBeiService;
import tbgt.service.ExpressCodeService;
import tbgt.service.OrderService;
import tbgt.util.TaobaoClientUtil;
import tbgt.web.criteria.OrderCriteria;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.web.paging.PagingEnabler;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private BaoBeiService baoBeiService;
    private OrderService orderService;
    private ExpressCodeService expressCodeService;

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
        mv.addObject("orders", new ArrayList());
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


    @RequestMapping(value = "/refresh", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refresh(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        orderService.refreshOrder(top_session,false);
        mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @RequestMapping(value = "/refreshAll", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refreshAll(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("order");
        orderService.refreshOrder(top_session,true);
        mv.addObject("status","WAIT_SELLER_SEND_GOODS");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ex.getMessage();
    }


}
