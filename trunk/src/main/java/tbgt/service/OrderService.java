package tbgt.service;


import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.web.criteria.OrderCriteria;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public void deleteOrder(int id);

    public Order getOrderById(int id);

    public void saveOrder(Order order);

    public void updateOrder(Order order);

    public Express getExpressByOrderId(int orderId);

    public void insertExpress(Express express);

    public void updateExpress(Express express);

    public void deleteExpress(int id);

    public List<Order> getOrders(OrderCriteria orderCriteria);
}
