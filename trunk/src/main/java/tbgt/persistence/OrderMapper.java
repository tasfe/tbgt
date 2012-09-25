package tbgt.persistence;

import tbgt.domain.Order;

import java.util.List;

public interface OrderMapper {

    public void saveOrder(Order order);

    public void updateOrder(Order order);

    public void deleteOrder(int id);

    public List<Order> getAllOrder();
}
