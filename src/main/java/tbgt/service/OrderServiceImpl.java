package tbgt.service;

import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.domain.SoldBaobei;
import tbgt.domain.BaobeiSku;
import tbgt.persistence.ExpressMapper;
import tbgt.persistence.OrderMapper;
import tbgt.persistence.SoldBaobeiMapper;
import tbgt.persistence.BaobeiSkuMapper;
import tbgt.web.criteria.OrderCriteria;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.util.TaobaoClientUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Trade;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradeFullinfoGetRequest;

public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private SoldBaobeiMapper soldBaobeiMapper;
    private BaobeiSkuMapper baobeiSkuMapper;
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

    public void setBaobeiSkuMapper(BaobeiSkuMapper baobeiSkuMapper) {
        this.baobeiSkuMapper = baobeiSkuMapper;
    }

    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    public List<Order> getAllNotClosedOrders() {
        return orderMapper.getAllOrders();
    }

    @Transactional
    public void deleteOrder(long id) {
        Order order = getOrderById(id);
        List<SoldBaobei> soldBaobeis = order.getSoldBaobeis();
        for (SoldBaobei soldBaobei : soldBaobeis) {
            soldBaobeiMapper.deleteSoldBaobei(soldBaobei.getId());
        }
        Express express = order.getExpress();
        if (express != null) {
          expressMapper.deleteExpress(express.getId());
        }

        orderMapper.deleteOrder(id);
    }

    public Order getOrderById(long id) {
        return orderMapper.getOrderById(id);
    }


    public List<Order> getOrders(OrderCriteria orderCriteria) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        PaginationTO paginationTO = PagingContextHolder.get();
        paraMap.put("sort","pay_time");
        paraMap.put("direction","asc");
        if (paginationTO != null) {
            paraMap.put("sort", paginationTO.getsSortColumn_0());
            paraMap.put("direction", paginationTO.getsSortDir_0());
        }
        if(orderCriteria!=null){
          paraMap.put("fromDate",orderCriteria.getFromDate());
          paraMap.put("toDate",orderCriteria.getToDate());
          paraMap.put("name",orderCriteria.getName());
          paraMap.put("status",orderCriteria.getStatus());
        }
        return orderMapper.getOrders(paraMap);
    }

    public void updateStatus(long orderId,String status){
        Map<String,Serializable> param = new HashMap<String, Serializable>();
        param.put("orderId",orderId);
        param.put("status",status);
        orderMapper.updateStatus(param);
    }

    @Transactional
    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
        List<SoldBaobei> soldBaobeis = order.getSoldBaobeis();
        for (SoldBaobei soldBaobei : soldBaobeis) {
            soldBaobeiMapper.saveSoldBaoBei(soldBaobei);
        }
    }

    @Transactional
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
        List<SoldBaobei> soldBaobeis = order.getSoldBaobeis();
        for (SoldBaobei soldBaobei : soldBaobeis) {
            soldBaobeiMapper.updateSoldBaoBei(soldBaobei);
        }
    }

    public Express getExpressByOrderId(long orderId){
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

    @Override
    @Transactional
    public  void refreshOrder(String top_session,boolean refreshAll) throws ApiException {
        TaobaoClient client = TaobaoClientUtil.getTaobaoClient();
        TradesSoldGetRequest req = new TradesSoldGetRequest();
        req.setFields("tid");
        req.setType("ec,fixed,auction,auto_delivery,cod,independent_shop_trade,independent_simple_trade,shopex_trade,netcn_trade,external_trade,hotel_trade,fenxiao,game_equipment,instant_trade,b2c_cod,super_market_trade,super_market_cod_trade,alipay_movie,taohua,waimai,nopaid");
//		req.setStartCreated(start);
//		req.setEndCreated(end);
        req.setPageSize(100L);
        if(!refreshAll){
          req.setStatus("WAIT_SELLER_SEND_GOODS");
          List<Order> orders = getAllNotClosedOrders();
            for (Order order: orders) {
                updateOrder(top_session, client, order.getId());
            }
        }
        req.setUseHasNext(true);

        long pageNo = 1;
        TradesSoldGetResponse rsp = null;
        do {
            req.setPageNo(pageNo);
            rsp = client.execute(req, top_session);
            if (rsp.isSuccess()) {
                for (Trade t : rsp.getTrades()) {
                    updateOrder(top_session, client, t.getTid());
                }
                pageNo++;
            }
        } while ((!rsp.isSuccess() || rsp.getHasNext()));
    }

    public  void refreshOneOrder(String top_session,long orderId) throws ApiException{
         updateOrder(top_session, TaobaoClientUtil.getTaobaoClient(), orderId);
    }

    private void updateOrder(String top_session, TaobaoClient client, long tid) throws ApiException {
        Trade trade = getTradeFullInfo(client, tid, top_session);
        Order order = getOrderById(trade.getTid());
        if(order==null){
                        order = new Order();
        }
        order.setId(trade.getTid());
        StringBuffer address = new StringBuffer();
        address.append(trade.getReceiverName()).append(" , ").append(trade.getReceiverMobile()).append(" , ")
                .append(trade.getReceiverPhone()).append(" , ").append(trade.getReceiverState()).append(" ")
                .append(trade.getReceiverCity()).append(" ").append(trade.getReceiverDistrict()).append(" ")
                .append(trade.getReceiverAddress()).append(" , ").append(trade.getReceiverZip());
        order.setReceiver_address(address.toString());
        order.setReceiver_state(trade.getReceiverState());
        order.setActualPrice(new BigDecimal(trade.getPayment()));
        order.setPay_time(trade.getPayTime());
        order.setStatus(trade.getStatus());
        order.setBuyer_msg(trade.getBuyerMessage());

        for (com.taobao.api.domain.Order tborder : trade.getOrders()) {
            SoldBaobei soldBaobei = new SoldBaobei();
            soldBaobei.setId(tborder.getOid());
            soldBaobei.setBbid(tborder.getNumIid());
            soldBaobei.setTid(trade.getTid());
            soldBaobei.setPrice(new BigDecimal(tborder.getPrice()));
            soldBaobei.setSku_id(tborder.getSkuId());
            soldBaobei.setSku_properties_name(tborder.getSkuPropertiesName());
            soldBaobei.setTitle(tborder.getTitle());
            soldBaobei.setQuantity(tborder.getNum());
            BaobeiSku baobeiSku =baobeiSkuMapper.getSkuById(Long.valueOf(tborder.getSkuId()));
            if (baobeiSku != null) {
                soldBaobei.setWeight(baobeiSku.getWeight());
                soldBaobei.setPurchase_price(baobeiSku.getPurchase_price());
            }
            order.addSoldBaobei(soldBaobei);
        }
        if(order.getModified()==null){
                       order.setModified(new Timestamp(trade.getModified().getTime()));
           saveOrder(order);
        }else if(!trade.getModified().equals(order.getModified())){
           order.setModified(new Timestamp(trade.getModified().getTime()));
           updateOrder(order);
        }
    }

    private Trade getTradeFullInfo(TaobaoClient client,Long tid, String sessionKey) throws ApiException {
		TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
        //currently, only get buyer_message
		req.setFields("buyer_message,status,tid,modified,orders,receiver_name, receiver_state, receiver_city, receiver_district, receiver_address, receiver_zip, receiver_mobile, receiver_phone,payment,pay_time");
		req.setTid(tid);
		TradeFullinfoGetResponse rsp = client.execute(req, sessionKey);
		if (rsp.isSuccess()) {
			return rsp.getTrade();
		}
		return null;
	}


    public boolean send(String top_session) throws ApiException {
        List<HashMap> orders = orderMapper.getPendingSendOrders();
        boolean success = false;
        for (HashMap order : orders) {
            TaobaoClient client = TaobaoClientUtil.getTaobaoClient();
            LogisticsOfflineSendRequest logisticsOfflineSendRequest = new LogisticsOfflineSendRequest();
            String expressno = (String) order.get("expressno");
//                YUNDA -- 韵达快运  EMS -- EMS
            logisticsOfflineSendRequest.setCompanyCode(expressno.startsWith("EF") ? "EMS" : "YUNDA");
            logisticsOfflineSendRequest.setOutSid(expressno);
            Long orderId = (Long) order.get("orderid");
            logisticsOfflineSendRequest.setTid(orderId);
            LogisticsOfflineSendResponse rsp = client.execute(logisticsOfflineSendRequest, top_session);
            success = rsp.getShipping().getIsSuccess();
            if(success){
                updateStatus(orderId,"WAIT_BUYER_CONFIRM_GOODS");
            }
        }
        return success;
    }
}
