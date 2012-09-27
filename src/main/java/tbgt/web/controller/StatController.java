package tbgt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.service.OrderService;
import tbgt.service.StatService;
import tbgt.web.criteria.OrderCriteria;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/stat")
public class StatController {
    private StatService statService;
    private OrderService orderService;

    @Autowired
    public void setStatService(StatService statService) {
        this.statService = statService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/profit", method = RequestMethod.GET)
    public ModelAndView viewDefaultProfit() {
        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.setFromDate(new Date());
        orderCriteria.setToDate(new Date());
        return profit(orderCriteria);
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public ModelAndView viewProfit(@ModelAttribute (value = "orderCriteria") OrderCriteria orderCriteria, BindingResult result) {
        return profit(orderCriteria);
    }

    public ModelAndView profit(OrderCriteria orderCriteria) {
        ModelAndView mv = new ModelAndView("stat");
        mv.addObject("criteria", orderCriteria);
        List<Order> orders = orderService.getOrders(orderCriteria);
        Map<String, BigDecimal> summary = getSummary(orders);
        mv.addObject("summary", summary);
        mv.addObject("orders", orders);
        return mv;
    }

    private Map<String, BigDecimal> getSummary(List<Order> orders) {
        Map<String, BigDecimal> summary = new HashMap<String, BigDecimal>();
        BigDecimal totalSaled = BigDecimal.ZERO;
        BigDecimal totalPurchase = BigDecimal.ZERO;
        BigDecimal totalExpress = BigDecimal.ZERO;
        BigDecimal totalAgencyFee = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;
        for(Order order: orders){
            totalSaled = totalSaled.add(order.getActualPrice());
            totalPurchase = totalPurchase.add(order.getPurchasePrice());
            Express express = order.getExpress();
            BigDecimal expressFee = express != null && express.getFee()!=null  ? express.getFee() : BigDecimal.ZERO;
            totalExpress = totalExpress.add(expressFee);
            totalAgencyFee = totalAgencyFee.add(order.getProfit());
            totalProfit = totalProfit.add(order.getProfit());
        }
        summary.put("totalSale",totalSaled);
        summary.put("totalPurchase",totalPurchase);
        summary.put("totalExpress",totalExpress);
        summary.put("totalAgencyFee",totalAgencyFee);
        summary.put("totalProfit",totalProfit);
        return summary;
    }


}
