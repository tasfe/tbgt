<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>易家生活坊宝贝跟踪 统计</title>
<link rel="stylesheet" type="text/css" href="/tbgt/css/theme.css" />
<link rel="stylesheet" type="text/css" href="/tbgt/css/style.css" />
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="/tbgt/css/ie-sucks.css" />
<![endif]-->

<link rel="stylesheet" type="text/css" href="/tbgt/css/custom/jquery-ui-1.8.23.custom.css"/>
<script type="text/javascript" src="/tbgt/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/tbgt/js/jquery-ui-1.8.23.custom.min.js"></script>
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>易家生活坊宝贝跟踪</h2>
     <div id="topmenu">
            	<ul>
                    <li ><a href="/tbgt/order/view.html">订单管理</a></li>
                	<li ><a href="/tbgt/baobei/view.html">宝贝管理</a></li>
                    <li class="current"><a href="/tbgt/stat/profit.html">统计</a></li>
                    <li><a href="#">设置</a></li>
              </ul>
      </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li><a href="#" class="profit">利润统计</a></li>
            		<li><a href="#" class="volume">销售量统计</a></li>
                </ul>
            </div>
      </div>
        <div id="wrapper">
            <div id="content">
                <div id="box">
                	<h3>利润</h3>

                    <form:form action="/tbgt/stat/profit.html" commandName="criteria" id="form">
                        <table width="100%">
                            <tr>
                                <th class="a-left">
                                    从&nbsp;
                                    <form:input path="fromDate" id="fromDate" class="date"/>
                                    &nbsp;到&nbsp;
                                    <form:input path="toDate" id="toDate" class="date"/>
                                    &nbsp;
                                    宝贝名
                                    <form:input path="name" id="name"/>
                                    &nbsp;
                                    <input id="saveBtn" type="submit" value="搜索" class="button"/>
                                </th>
                            </tr>

                        </table>
                    </form:form>
                       <br/>

                    <table width="100%">
                        <tr>
                            <td width="200px" class="f_name">总销售额</td>
                            <td>${summary.totalSale}</td>
                        </tr>
                        <tr>
                            <td width="200px" class="f_name">总成本</td>
                            <td>${summary.totalPurchase}</td>
                        </tr>
                        <tr>
                            <td width="200px" class="f_name">总快递费</td>
                            <td>${summary.totalExpress}</td>
                        </tr>
                        <tr>
                            <td width="200px" class="f_name">总代理费</td>
                            <td>${summary.totalAgencyFee}</td>
                        </tr>
                        <tr>
                            <td width="70px" class="f_name">总利润</td>
                            <td>${summary.totalProfit}</td>
                        </tr>

                    </table>

                    <br/>
                	<table width="100%">
						<thead>
							<tr>
                            	<th width="70px">订单号</th>
                            	<th>订单详情</th>
								<th width="150px">成交时间</th>
								<th width="150px">成交价</th>
                                <th width="100px">成本</th>
                                <th width="100px">快递费用</th>
                                <th width="100px">代理费</th>
                                <th width="100px">利润</th>
                            </tr>
						</thead>
						<tbody>
                        <c:forEach items="${orders}" var="order" varStatus="index">
							<tr>
                            	<td class="a-center"><a href="#">${order.id}</a></td>
                            	<td>
                                  <c:forEach items="${order.soldBaobeis}" var="soldBaobei">
                                    ${soldBaobei.quantity} 个 ${soldBaobei.name} <br/>
                                  </c:forEach>
                                </td>
								<td>${order.soldTime}</td>
                                <td class="a-right">${order.actualPrice}</td>
                                <td class="a-right">${order.purchasePrice}</td>
                                <td class="a-right">${order.express.fee}</td>
                                <td class="a-right">${order.agencyFee}</td>
                                <td class="a-right">${order.profit}</td>
                            </tr>
                        </c:forEach>
						</tbody>
					</table>

                </div>
                <br />
            </div>

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
    $(function() {
		$(".date" ).datepicker({ dateFormat: "yy-mm-dd"});
	});
</script>