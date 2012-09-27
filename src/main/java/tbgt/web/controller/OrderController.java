package tbgt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Baobei;
import tbgt.domain.Order;
import tbgt.domain.Price;
import tbgt.domain.SoldBaobei;
import tbgt.service.BaoBeiService;
import tbgt.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private BaoBeiService baoBeiService;
    private OrderService orderService;

    @Autowired
    public void setBaoBeiService(BaoBeiService baoBeiService) {
        this.baoBeiService = baoBeiService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewOrder() {
        ModelAndView mv = new ModelAndView("order");
        mv.addObject("order", new Order());
        return mv;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public ModelAndView order(@RequestParam String baobeiIdsStr) {
        String[] baobeiIds = baobeiIdsStr.split(",");
        ModelAndView mv = new ModelAndView("orderDetail");
        Order order = new Order();
        List<SoldBaobei> soldBaobeis = new ArrayList<SoldBaobei>();
        for (String baobeiIdStr : baobeiIds) {
            SoldBaobei soldBaobei = new SoldBaobei();
            Integer baobeiId = Integer.valueOf(baobeiIdStr);
            Baobei baobei = baoBeiService.getBaobeiById(baobeiId);
            soldBaobei.setBaobeiId(baobeiId);
            soldBaobei.setName(baobei.getName());
            soldBaobei.setSalePrice(baobei.getPrice().getSalePrice());
            soldBaobeis.add(soldBaobei);
        }
        order.setSoldBaobeis(soldBaobeis);
        mv.addObject("order", order);
        return mv;
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public ModelAndView updateOrder(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("orderDetail");

        mv.addObject("order", new Order());
        return mv;
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public ModelAndView deleteOrder(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("order");
        System.out.println("delete order = " + id);
        List<Order> orders = new ArrayList<Order>();

        mv.addObject("orders", orders);
        return mv;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveOrder(@ModelAttribute(value = "order") Order order, BindingResult result) {
        ModelAndView mv = new ModelAndView("order");
        //todo..validator
        if (!result.hasErrors()) {
            if (order.getId() == 0) {
                orderService.saveOrder(order);
            } else {
                orderService.updateOrder(order);
            }
//            order.setOrderNo(String.valueOf(new Random(1000).nextInt()));
//            mv.addObject("order", order);
        }
//        List<Order> orders = new ArrayList<Order>();

        mv.addObject("baobeis", baoBeiService.getAllBaobei());
//        mv.addObject("orders", orderService.getAllOrders());
        return mv;
    }

}
