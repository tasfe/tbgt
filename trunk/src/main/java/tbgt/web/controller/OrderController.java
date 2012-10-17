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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        mv.addObject("status","P");
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

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView confirm(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("order");
        orderService.updateStatus(id,"C");
        mv.addObject("status","C");
        return mv;
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public ModelAndView deleteOrder(@RequestParam int id) {
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
    public ModelAndView express(@RequestParam int orderId) {
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
    public @ResponseBody int getExpressFee(@RequestParam int orderId,@RequestParam String expressNo){

        Order order = orderService.getOrderById(orderId);
        String address = order.getReceiver_address();
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
        TaobaoClient client = TaobaoClientUtil.getTaobaoClient();
        TradesSoldGetRequest req = new TradesSoldGetRequest();
        req.setFields("tid");
        req.setType("ec,fixed,auction,auto_delivery,cod,independent_shop_trade,independent_simple_trade,shopex_trade,netcn_trade,external_trade,hotel_trade,fenxiao,game_equipment,instant_trade,b2c_cod,super_market_trade,super_market_cod_trade,alipay_movie,taohua,waimai,nopaid");
//		req.setStartCreated(start);
//		req.setEndCreated(end);
        req.setPageSize(100L);
        req.setStatus("WAIT_BUYER_PAY");
        req.setUseHasNext(true);

        long pageNo = 1;
        TradesSoldGetResponse rsp = null;
        do {
            req.setPageNo(pageNo);
            rsp = client.execute(req, top_session);
            if (rsp.isSuccess()) {
                for (Trade t : rsp.getTrades()) {
                    Trade trade = getTradeFullInfo(client, t.getTid(), top_session);
                    Order order = new Order();
                    order.setId(trade.getTid());
                    StringBuffer address = new StringBuffer();
                    address.append(trade.getReceiverName()).append(" , ").append(trade.getReceiverMobile()).append(" , ")
                            .append(trade.getReceiverPhone()).append(" , ").append(trade.getReceiverState()).append(" , ")
                            .append(trade.getReceiverCity()).append(" , ").append(trade.getReceiverDistrict()).append(" , ")
                            .append(trade.getReceiverAddress()).append(" , ").append(trade.getReceiverZip());
                    order.setReceiver_address(address.toString());
                    order.setReceiver_state(trade.getReceiverState());
                    order.setActualPrice(new BigDecimal(trade.getPayment()));
                    order.setPay_time(trade.getPayTime());
                    order.setStatus(trade.getStatus());
                    order.setBuyer_msg(trade.getBuyerMessage());
                    for (com.taobao.api.domain.Order tborder : trade.getOrders()) {
                        SoldBaobei soldBaobei = new SoldBaobei();
                        soldBaobei.setId(tborder.getOid());
                        soldBaobei.setBbid(tborder.getNumIid());
                        soldBaobei.setTid(trade.getTid());
                        soldBaobei.setPrice(new BigDecimal(tborder.getPrice()));
                        soldBaobei.setSku_id(tborder.getSkuId());
                        soldBaobei.setSku_properties_name(tborder.getSkuPropertiesName());
                        soldBaobei.setTitle(tborder.getTitle());
                        soldBaobei.setQuantity(tborder.getNum());
                        BaobeiSku baobeiSku = baoBeiService.getSkuById(Long.valueOf(tborder.getSkuId()));
                        if (baobeiSku != null) {
                            soldBaobei.setWeight(baobeiSku.getWeight());
                            soldBaobei.setPurchase_price(baobeiSku.getPurchase_price());
                        }
                        order.addSoldBaobei(soldBaobei);
                    }
                    orderService.saveOrder(order);
                }
                pageNo++;
            }
        } while ((!rsp.isSuccess() || rsp.getHasNext()));
        return mv;
    }

    private Trade getTradeFullInfo(TaobaoClient client,Long tid, String sessionKey) throws ApiException {
		TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
        //currently, only get buyer_message
		req.setFields("buyer_message,status,tid,,orders,receiver_name, receiver_state, receiver_city, receiver_district, receiver_address, receiver_zip, receiver_mobile, receiver_phone,payment,pay_time");
		req.setTid(tid);
		TradeFullinfoGetResponse rsp = client.execute(req, sessionKey);
		if (rsp.isSuccess()) {
			return rsp.getTrade();
		}
		return null;
	}

}
