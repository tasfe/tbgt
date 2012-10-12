package tbgt.web.controller;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.service.OrderService;
import tbgt.service.StatService;
import tbgt.web.criteria.OrderCriteria;
import tbgt.util.DateUtil;

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
        orderCriteria.setFromDate(new DateTime().dayOfMonth().withMinimumValue().toDate());
        orderCriteria.setToDate(new Date());
        return profit(orderCriteria);
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public ModelAndView viewProfit(@ModelAttribute (value = "orderCriteria") OrderCriteria orderCriteria, BindingResult result) {
        return profit(orderCriteria);
    }


    @RequestMapping(value = "/lastweek", method = RequestMethod.POST)
    public @ResponseBody String lastweek() {
       return getChartData(new DateTime().minusDays(7).toDate(),new Date());
    }

    @RequestMapping(value = "/lastmonth", method = RequestMethod.POST)
    public @ResponseBody String lastmonth() {
      return getChartData(new DateTime().minusDays(30).toDate(),new Date());
    }

    private String getChartData(Date fromDate, Date toDate){
        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.setFromDate(fromDate);
        orderCriteria.setToDate(toDate);
        orderCriteria.setStatus("C");
        List<Order> orders = orderService.getOrders(orderCriteria);
        Map<String, BigDecimal> chartDataMap = new HashMap<String, BigDecimal>();
        for (Order order : orders) {
            String soldDate = new DateTime(order.getSoldTime()).toString(DateUtil.DATE_FORMAT);
            BigDecimal profit = chartDataMap.get(soldDate);
            if (profit != null) {
                profit = profit.add(order.getProfit());
            }else{
                profit = order.getProfit();
            }
            chartDataMap.put(soldDate, profit);
        }
        StringBuffer buff = new StringBuffer();
        buff.append("[");
        for(Map.Entry<String, BigDecimal> entry:chartDataMap.entrySet()){
          buff.append("['").append(entry.getKey()).append("',").append(entry.getValue().toString()).append("],");
        }
        buff.append("]");
        return buff.toString();
    }

    public ModelAndView profit(OrderCriteria orderCriteria) {
        ModelAndView mv = new ModelAndView("stat");
        mv.addObject("criteria", orderCriteria);
        orderCriteria.setStatus("C");
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
        BigDecimal totalPaid = BigDecimal.ZERO;
        for(Order order: orders){
            totalSaled = totalSaled.add(order.getActualPrice());
            totalPurchase = totalPurchase.add(order.getPurchasePrice());
            Express express = order.getExpress();
            BigDecimal expressFee = express != null   ? express.getFee() : BigDecimal.ZERO;
            totalExpress = totalExpress.add(expressFee);
            totalAgencyFee = totalAgencyFee.add(order.getAgencyFee());
            totalProfit = totalProfit.add(order.getProfit());
        }
        totalPaid = totalPurchase.add(totalExpress);
        totalPaid = totalPaid.add(totalAgencyFee);
        summary.put("totalSale",totalSaled);
        summary.put("totalPurchase",totalPurchase);
        summary.put("totalExpress",totalExpress);
        summary.put("totalAgencyFee",totalAgencyFee);
        summary.put("totalProfit",totalProfit);
        summary.put("totalPaid",totalPaid);
        return summary;
    }


}
