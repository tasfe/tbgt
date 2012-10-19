package tbgt.service;


import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.web.criteria.OrderCriteria;

import java.util.List;

import com.taobao.api.ApiException;

public interface OrderService {

    public List<Order> getAllOrders();

    public List<Order> getAllNotClosedOrders();

    public void deleteOrder(long id);

    public Order getOrderById(long id);

    public void saveOrder(Order order);

    public void updateOrder(Order order);

    public Express getExpressByOrderId(long orderId);

    public void insertExpress(Express express);

    public void updateExpress(Express express);

    public void deleteExpress(int id);

    public List<Order> getOrders(OrderCriteria orderCriteria);

    public void updateStatus(long orderId,String status);

    public  void refreshOrder(String top_session,boolean refreshAll) throws ApiException;

    public  void refreshOneOrder(String top_session,long orderId) throws ApiException;

    public  boolean send(String top_session) throws ApiException;
}
