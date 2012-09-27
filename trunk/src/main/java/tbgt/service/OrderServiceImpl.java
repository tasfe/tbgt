package tbgt.service;

import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.domain.SoldBaobei;
import tbgt.persistence.ExpressMapper;
import tbgt.persistence.OrderMapper;
import tbgt.persistence.SoldBaobeiMapper;
import tbgt.web.criteria.OrderCriteria;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private SoldBaobeiMapper soldBaobeiMapper;
    private ExpressMapper expressMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void setSoldBaobeiMapper(SoldBaobeiMapper soldBaobeiMapper) {
        this.soldBaobeiMapper = soldBaobeiMapper;
    }

    public void setExpressMapper(ExpressMapper expressMapper) {
        this.expressMapper = expressMapper;
    }

    public List<Order> getAllOrders() {
        return orderMapper.getAllOrder();
    }

    @Transactional
    public void deleteOrder(int id) {
        Order order = getOrderById(id);
        List<SoldBaobei> soldBaobeis = order.getSoldBaobeis();
        for (SoldBaobei soldBaobei : soldBaobeis) {
            soldBaobeiMapper.deleteSoldBaobei(soldBaobei.getId());
        }
        Express express = order.getExpress();
        expressMapper.deleteExpress(express.getId());

        orderMapper.deleteOrder(id);
    }

    public Order getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }

    public List<Order> getOrders(OrderCriteria orderCriteria) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        PaginationTO paginationTO = PagingContextHolder.get();
        paraMap.put("sort","soldtime");
        paraMap.put("direction","asc");
        if (paginationTO != null) {
            paraMap.put("sort", paginationTO.getsSortColumn_0());
            paraMap.put("direction", paginationTO.getsSortDir_0());
        }
        if(orderCriteria!=null){
          paraMap.put("fromDate",orderCriteria.getFromDate());
          paraMap.put("toDate",orderCriteria.getToDate());
          paraMap.put("name",orderCriteria.getName());
          paraMap.put("expressInd",orderCriteria.getExpressInd());
        }
        return orderMapper.getOrders(paraMap);
    }

    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
        List<SoldBaobei> soldBaobeis = order.getSoldBaobeis();
        for (SoldBaobei soldBaobei : soldBaobeis) {
            soldBaobei.setOrderId(order.getId());
            soldBaobeiMapper.saveSoldBaoBei(soldBaobei);
        }
    }

    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    public Express getExpressByOrderId(int orderId){
        return expressMapper.getExpressByOrderId(orderId);
    }

    @Override
    @Transactional
    public void insertExpress(Express express) {
        expressMapper.insertExpress(express);
    }

    @Override
    @Transactional
    public void updateExpress(Express express) {
        expressMapper.updateExpress(express);
    }

    @Override
    public void deleteExpress(int id) {
        expressMapper.deleteExpress(id);
    }
}
