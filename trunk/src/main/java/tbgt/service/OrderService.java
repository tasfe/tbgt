package tbgt.service;


import tbgt.domain.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public void deleteOrder(int id);

    public Order getOrderById(int id);

    public void saveOrder(Order order);

    public void updateOrder(Order order);
}
