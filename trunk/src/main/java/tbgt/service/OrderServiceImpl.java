package tbgt.service;

import tbgt.domain.Order;
import tbgt.domain.SoldBaobei;
import tbgt.persistence.OrderMapper;
import tbgt.persistence.SoldBaobeiMapper;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private SoldBaobeiMapper soldBaobeiMapper;


    public List<Order> getAllOrders() {
        return orderMapper.getAllOrder();
    }

    public void deleteOrder(int id) {
        orderMapper.deleteOrder(id);
    }

    public Order getOrderById(int id) {
        return orderMapper.getOrderById(id);
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

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public SoldBaobeiMapper getSoldBaobeiMapper() {
        return soldBaobeiMapper;
    }

    public void setSoldBaobeiMapper(SoldBaobeiMapper soldBaobeiMapper) {
        this.soldBaobeiMapper = soldBaobeiMapper;
    }
}
