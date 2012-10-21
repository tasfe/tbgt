<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>易家生活坊宝贝跟踪 订单管理</title>
<link rel="shortcut icon" href="<tbgt:constant name="ContextPath"/>/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/theme.css" />
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/style.css" />
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/ie-sucks.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/custom/jquery-ui-1.8.23.custom.css"/>
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jquery.dataTables.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/datatable_jui.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/EMSwitchBox.css" type="text/css" media="screen" charset="utf-8">
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jNotify.jquery.css" type="text/css" media="screen" charset="utf-8">

<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.form.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/EMSwitchBox.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jNotify.jquery.js"></script>
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>易家生活坊宝贝跟踪</h2>
     <div id="topmenu">
            	<ul>
                    <li class="current"><a href="<tbgt:constant name="ContextPath"/>/order/view.html">订单管理</a></li>
                	<li ><a href="<tbgt:constant name="ContextPath"/>/baobei/view.html">宝贝管理</a></li>
                    <li><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html">统计</a></li>
                    <li><a href="<tbgt:constant name="ContextPath"/>/tools/express.html">工具</a></li>
              </ul>
      </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
            		<li><a href="#" onclick="showOrders('WAIT_SELLER_SEND_GOODS');return false" class="pending">未发货</a></li>
                    <li><a href="#" onclick="showOrders('WAIT_BUYER_CONFIRM_GOODS');return false" class="express">已发货</a></li>
                    <li><a href="#" onclick="showOrders('TRADE_FINISHED');return false" class="edit">交易成功</a></li>
                    <li><a href="#" onclick="refresh();return false" class="refresh">增量同步订单</a></li>
                    <li><a href="#" onclick="refreshAll();return false" class="refresh">同步所有订单</a></li>
                    <li><a href="#" onclick="send();return false" class="express">淘宝发货</a></li>
                </ul>
            </div>
      </div>
        <div id="wrapper">
            <div id="content">
                    <h3>订单列表（<span id='status'>未发货</span>）</h3>
                	<table id="tdata1" class="display" cellspacing="0" cellpadding="0" border="0" width="100%">
                        <thead>
							<tr>
                            	<th width="70px">订单号</th>
                            	<th>订单详情</th>
								<th width="150px">成交时间</th>
                                <th width="100px">成交价格</th>
                                <th width="100px">快递单号</th>
                                <th width="100px">快递费用</th>
                                <th width="100px">操作</th>
                            </tr>
						</thead>
						
                        <tbody>
                          <td colspan="7" class="dataTables_empty">加载数据.....</td>
                        </tbody>
					</table>

                </div>
                <br />
            </div>

        <div id="footer">
           <div id="credits">
   		        版权所有 © 2012-2014, 易家生活坊, All Rights Reserved.
           </div>
        </div>
</div>
</body>
</html>

<script type="text/javascript">
     var status='${status}';
     function refresh(orderId){
        if (orderId != null && orderId != "") {
            if (confirm("即将同步该笔订单，确认手没抖吗?")) {
                window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/order/refreshOrder.html?orderId="+orderId+"%26status="+status;
            }
            return;
        }
        if (confirm("即将增量同步订单，确认手没抖吗?")) {
            window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/order/refresh.html";
        }
    }
     function refreshAll(){
        if (confirm("即将同步所有订单，确认手没抖吗?")) {
            window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/order/refreshAll.html";
        }
    }
     function send(){
        if (confirm("即将到淘宝对所有填了订单号但是没有发货的订单进行发货，确认手没抖吗?")) {
            window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/order/send.html";
        }
    }
     
    function express(orderId) {
        var dialog = $('<div style="display:none"></div>').appendTo('body');
        // load remote content
        var url = "<tbgt:constant name='ContextPath'/>/order/express.html?orderId="+orderId+"&status="+status;
        dialog.load(
                url,
                function (responseText, textStatus, XMLHttpRequest) {
                    dialog.dialog({
                        title: "快递详情",
                        modal: true,
                        width: 1024,
                        height: 600,
                        close: function(event, ui) {
                            dialog.remove();
                        }
                    });
                }
        );
        //prevent the browser to follow the link
        return false;
    }
     function viewExpressStatus(orderId) {
        var dialog = $('<div style="display:none"></div>').appendTo('body');
        // load remote content
        var url = "<tbgt:constant name='ContextPath'/>/order/viewExpressStatus.html?orderid="+orderId;
        dialog.load(
                url,
                function (responseText, textStatus, XMLHttpRequest) {
                    dialog.dialog({
                        title: "物流详情",
                        modal: true,
                        width: 1000,
                        height: 600,
                        close: function(event, ui) {
                            dialog.remove();
                        }
                    });
                }
        );
        //prevent the browser to follow the link
        return false;
    }
    <%--var orderDialog;--%>
    <%--function updateOrder(action,id) {--%>
        <%--orderDialog = $('<div style="display:none"></div>').appendTo('body');--%>
        <%--// load remote content--%>
        <%--var url = "<tbgt:constant name='ContextPath'/>/order/"+action+".html?id="+id;--%>
        <%--orderDialog.load(--%>
                <%--url,--%>
                <%--function (responseText, textStatus, XMLHttpRequest) {--%>
                    <%--orderDialog.dialog({--%>
                        <%--title: "订单详情",--%>
                        <%--modal: true,--%>
                        <%--width: 1100,--%>
                        <%--height: 600,--%>
                        <%--close: function(event, ui) {--%>
                            <%--orderDialog.remove();--%>
                        <%--}--%>
                    <%--});--%>
                <%--}--%>
        <%--);--%>
        <%--//prevent the browser to follow the link--%>
        <%--return false;--%>
    <%--}--%>

    function deleteOrder(orderNo){
        if(confirm("确认要删除该订单吗?")){
            var form = $("<form></form>");
            form.attr('action', "<tbgt:constant name='ContextPath'/>/order/deleteOrder.html?id=" + orderNo);
            form.attr('method', 'post');
            form.appendTo("body");
            form.css('display', 'none');
            form.submit()
        }
    }

    var tdata1;

    $('#status').html(status=='WAIT_SELLER_SEND_GOODS'?"未发货":(status=='WAIT_BUYER_CONFIRM_GOODS'?"已发货":"交易成功"));
    var gaiSelected = [];
    $(document).ready(function() {
        tdata1 = $('#tdata1').dataTable({
            "sPaginationType": "full_numbers",
            "bJQueryUI": true,
            'iDisplayLength': 50,
            "aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "bStateSave": true,
            "oLanguage": {
                "sProcessing": "系统处理中",
                "sLoadingRecords": "请等待，数据载入中.....",
                "sLengthMenu": "每页显示 _MENU_ 记录",
                "sInfo": "第 _START_ 到 _END_ 条, 总共 _TOTAL_ 条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索名称" ,
                "sZeroRecords": "没有订单",
                "sInfoEmpty": "",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sLast": "最后一页",
                    "sNext": "下一页",
                    "sPrevious": "上一页"
                }
            },
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": "<tbgt:constant name='ContextPath'/>/order/list.html",
            "fnServerParams": function (aoData) {
                aoData.push({ "name": "status", "value": status });
            },
            "aoColumns": [
                { "mData": "id" },
                { "mData": "receiver_address" },
                { "mData": "pay_time" },
                { "mData": "actualPrice" },
                { "mData": "express.expressNo" },
                { "mData": "express.fee" },
                { "mData": null }
            ],
            "aoColumnDefs": [
                { "bSortable" :false, "aTargets": [ 0 ],"sWidth": "70px"},
                { "bSortable" :false, "aTargets": [ 1 ],  "sClass":"nowrap"},
                { "bSortable" :true, "aTargets": [ 2 ],  "sWidth": "100px"},
                { "bSortable" :true, "aTargets": [ 3 ],  "sWidth": "100px","sClass":"a-right"},
                { "bSortable": true, "aTargets": [ 4 ],  "sWidth": "80px" },
                { "bSortable": true, "aTargets": [ 5 ],  "sWidth": "80px","sClass":"a-right" },
                { "bSortable": false, "aTargets": [6 ],  "sWidth": "80px" }
            ],
            "aaSorting": [
                [ 2, "desc" ]
            ],
            "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                    $('td:eq(6)', nRow).html('<a href="#" onclick="express(\''+aData.id+'\');return false" title="快递"><img src="<tbgt:constant name="ContextPath"/>/images/icons/express.png" width="16" height="16" alt="快递"/></a><a href="#" title="同步订单" onclick="refresh(\''+aData.id+'\');return false"><img src="<tbgt:constant name="ContextPath"/>/images/icons/refresh.png" width="16" height="16" alt="同步订单"/></a> <a href="#" title="删除订单" onclick="deleteOrder(\''+aData.id+'\');return false"><img src="<tbgt:constant name="ContextPath"/>/images/icons/delete.png" width="16" height="16" alt="删除订单"/></a>');

                    $('td:eq(1)', nRow).html(function(){
                         var detail = "<span class='title'>地址 : </span><br/>" + aData.receiver_address + "<br/><br/><span class='title'>商品详情 : </span>";
                         for(var i = 0 ;  i <  aData.soldBaobeis.length ; i ++){
                             var soldbaobei = aData.soldBaobeis[i];
                             detail = detail + "<br/>" + soldbaobei.title +" ， 数量 ： "+ soldbaobei.quantity + "个"  + " ， " + soldbaobei.sku_properties_name;
                         }
                        if(aData.buyer_msg!=null){
                         detail = detail + "<br/><br/><span class='title'>买家留言 : </span>" + aData.buyer_msg;
                        };
                         return detail;
                    });
                   $('td:eq(4)', nRow).html(aData.express.id==0?"":(aData.express.expressNo+'<br/><a href="#" onclick="viewExpressStatus(\''+aData.id+'\');return false" title="物流信息">物流信息</a>'));
                   $('td:eq(5)', nRow).html(aData.express.id==0?"":aData.express.fee);
            }

        });

//        /* Click event handler */
//        $('#tdata1 tbody tr').live('click', function () {
//            var aData = tdata1.fnGetData(this);
//            var id = aData.id;
//
//            if (jQuery.inArray(id, gaiSelected) == -1) {
//                gaiSelected[gaiSelected.length++] = id;
//            }
//            else {
//                gaiSelected = jQuery.grep(gaiSelected, function(value) {
//                    return value != id;
//                });
//            }
////            console.log(gaiSelected);
//            $(this).toggleClass('row_selected');
//        });
//
//
//        $("#tdata1 tbody tr").live('dblclick', function () {
//            updateOrder('updateOrder',tdata1.fnGetData(this).id);
//        });
        if('${errormsg}'!=null && '${errormsg}'!='')
        {
           jError(
                        '${errormsg}',
                        {
                            autoHide : true, // added in v2.0
                            clickOverlay : false, // added in v2.0
                            MinWidth : 400,
                            TimeShown : 10000,
                            ShowTimeEffect : 200,
                            HideTimeEffect : 200,
                            LongTrip :20,
                            HorizontalPosition : 'center',
                            VerticalPosition : 'top',
                            ShowOverlay : false,
                            ColorOverlay : '#000',
                            OpacityOverlay : 0.3,
                            onClosed : function() { // added in v2.0

                            },
                            onCompleted : function() { // added in v2.0

                            }
                        });
        }


    });
    function showOrders(ind){
        status = ind;
        $('#status').html(ind=='WAIT_SELLER_SEND_GOODS'?"未发货":(ind=='WAIT_BUYER_CONFIRM_GOODS'?"已发货":"交易成功"));
        tdata1.fnDraw();
    }

</script>