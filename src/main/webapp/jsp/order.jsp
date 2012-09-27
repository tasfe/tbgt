<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>易家生活坊宝贝跟踪 订单管理</title>
<link rel="stylesheet" type="text/css" href="/tbgt/css/theme.css" />
<link rel="stylesheet" type="text/css" href="/tbgt/css/style.css" />
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="/tbgt/css/ie-sucks.css" />
<![endif]-->
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>易家生活坊宝贝跟踪</h2>
     <div id="topmenu">
            	<ul>
                    <li class="current"><a href="/tbgt/order/view.html">订单管理</a></li>
                	<li ><a href="/tbgt/baobei/view.html">宝贝管理</a></li>
                    <li><a href="/tbgt/stat/profit.html">统计</a></li>
                    <li><a href="#">设置</a></li>
              </ul>
      </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li><a href="#" class="edit">修改订单</a></li>
            		<li><a href="#" class="delete">删除订单</a></li>
                    <li><a href="#" class="express">快递</a></li>
                </ul>
            </div>
      </div>
        <div id="wrapper">
            <div id="content">
                <div id="box">
                    <h3>订单列表</h3>
                	<table width="100%">
                        <thead>
							<tr>
                            	<th width="70px"><a href="#">订单号<img src="/tbgt/images/icons/arrow_down_mini.gif" width="16" height="16" align="middle" /></a></th>
                            	<th><a href="#">订单详情</a></th>
								<th width="150px"><a href="#">成交时间</a></th>
                                <th width="100px"><a href="#">快递单号</a></th>
                                <th width="100px"><a href="#">快递费用</a></th>
                                <th width="100px"></th>
                            </tr>
						</thead>
						<tbody>
							<tr>
                            	<td class="a-center"><a href="#">232</a></td>
                            	<td>
                                   收货地址 ：黄伟 ，13913149392 ，0512-62886969-7032 ，江苏省 苏州市 园区 苏州工业园区星湖街328号科技园五期B11栋 ，8楼801室 ，215021
                                   <a href="#">订单详情</a>
                                </td>
								<td>2012-09-08 09:09:19</td>
                                <td class="a-right">1234567890099887</td>
                                <td class="a-right">5.00</td>
                                <td>
                                    <a href="#"><img src="/tbgt/images/icons/express.png" width="16" height="16" alt="快递"/></a>
                                    <a href="#"><img src="/tbgt/images/icons/edit.png" width="16" height="16" alt="修改宝贝"/></a>
                                    <a href="#"><img src="/tbgt/images/icons/delete.png" width="16" height="16" alt="删除宝贝"/></a>
                                 </td>
                            </tr>

						</tbody>
					</table>

                </div>
                <br />
            </div>

      </div>
        <div id="footer">
           <div id="credits">
   		        版权所有2012
           </div>
        </div>
</div>
</body>
</html>