package tbgt.persistence;

import tbgt.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    public void saveOrder(Order order);

    public void updateOrder(Order order);

    public void deleteOrder(int id);

    public List<Order> getAllOrder();

    public Order getOrderById(int id);

    public List<Order> getOrders(Map criteria);

    public void updateStatus(Map param);
}
