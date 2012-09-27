package tbgt.service;

import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.domain.SoldBaobei;
import tbgt.persistence.ExpressMapper;
import tbgt.persistence.OrderMapper;
import tbgt.persistence.SoldBaobeiMapper;

import java.util.List;

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
