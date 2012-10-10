<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>易家生活坊宝贝跟踪 工具</title>
<link rel="shortcut icon" href="<tbgt:constant name="ContextPath"/>/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/theme.css" />
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/style.css" />
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/ie-sucks.css" />
<![endif]-->

<link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/custom/jquery-ui-1.8.23.custom.css"/>
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jquery.dataTables.css" type="text/css" media="screen, projection">
<link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/datatable_jui.css" type="text/css" media="screen, projection">
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.dataTables.min.js"></script>
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>易家生活坊宝贝跟踪</h2>
     <div id="topmenu">
            	<ul>
                    <li ><a href="<tbgt:constant name="ContextPath"/>/order/view.html">订单管理</a></li>
                	<li ><a href="<tbgt:constant name="ContextPath"/>/baobei/view.html">宝贝管理</a></li>
                    <li ><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html">统计</a></li>
                    <li class="current"><a href="<tbgt:constant name="ContextPath"/>/tools/express.html">工具</a></li>
              </ul>
      </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li><a href="#" class="calc">快递费用计算</a></li>
                </ul>
            </div>
      </div>
        <div id="wrapper">
            <div id="content">
                <div id="box">
                	<h3>邮资</h3>
                     <tbgt:constant name='ContextPath' var="ContextPath"/>
                    <form:form action="${ContextPath}/tools/express.html" commandName="expressCodeCriteria" id="form">
                        <table width="100%">
                            <tr>
                                <th class="a-left">
                                    省份
                                    <form:input path="province" id="province"/>
                                    &nbsp;
                                    重量
                                    <form:input path="weight" id="weight"/>
                                    &nbsp;
                                    <input id="searchBtn" type="submit" value="搜索" class="button"/>
                                </th>
                            </tr>

                        </table>
                    </form:form>
                       <br/>



                    <br/>
                	<table id="tdata1" class="display" cellspacing="0" cellpadding="0" border="0" width="100%">
						<thead>
							<tr>
                            	<th width="100px">省份</th>
                            	<th width="70px">类型</th>
                            	<th width="60px">起重</th>
								<th width="100px">起价</th>
								<th width="60px">续重加价</th>
                                <th width="60px">1kg价(韵达)</th>
                                <th width="60px">到达天数</th>
                                <th width="80px">实际价（指定重量）</th>
                            </tr>
						</thead>
						<tbody>
						<c:set var="prvProvince"></c:set>
                        <c:forEach items="${expressCodes}" var="excd" varStatus="index">
							<tr>
                                
                                <c:if test="${prvProvince != null && prvProvince eq excd.province}">
                                    <script type="text/javascript">
                                        $("#province"+${index.index-1}).attr("rowspan",2);
                                    </script>
                                </c:if>
                                <c:if test="${prvProvince == null || prvProvince ne excd.province}">
                                   <td id="province${index.index}" class="a-center">${excd.province}</td>
                                </c:if>
								<c:set var="prvProvince">${excd.province}</c:set>
                            	<td class="a-center">${excd.type}</td>
                            	<td class="a-right">${excd.init_weight}</td>
								<td class="a-right">${excd.init_price}</td>
                                <td class="a-right">${excd.increase_price}</td>
                                <td class="a-right">
									 <c:if test="${excd.less1kg_price != 0 }">
									   ${excd.less1kg_price}
									 </c:if>
								</td>
                                <td class="a-right">${excd.days}</td>
                                <td class="a-right">
									<c:if test="${excd.calcPrice != 0 }">
									   ${excd.calcPrice}
									 </c:if>
								</td>
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

</script>