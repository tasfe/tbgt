<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache" />
    <title>易家生活坊宝贝跟踪 宝贝管理</title>
    <link rel="stylesheet" type="text/css" href="/tbgt/css/theme.css"/>
    <link rel="stylesheet" type="text/css" href="/tbgt/css/style.css"/>
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="/tbgt/css/ie-sucks.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/tbgt/css/cupertino/jquery-ui-1.8.23.custom.css"/>
    <script type="text/javascript" src="/tbgt/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="/tbgt/js/jquery-ui-1.8.23.custom.min.js"></script>
</head>

<body>
<div id="container">
    <div id="header">
        <h2>易家生活坊宝贝跟踪</h2>

        <div id="topmenu">
            <ul>
                <li><a href="/tbgt/baobei/order.html">订单管理</a></li>
                <li class="current"><a href="/tbgt/baobei/view.html">宝贝管理</a></li>
                <li><a href="#">统计</a></li>
                <li><a href="#">设置</a></li>
            </ul>
        </div>
    </div>
    <div id="top-panel">
        <div id="panel">
            <ul>
                <li><a href="#" onclick="saveBaobei('newBaobei');return false" class="add">添加新宝贝</a></li>
                <li><a href="#" class="delete">删除宝贝</a></li>
                <li><a href="#" class="order">添加订单</a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <div id="content">
            <div id="box">
                <h3>宝贝列表</h3>
                <c:if test="${!empty baobeis}">
                    <table width="100%">
                        <thead>
                        <tr>
                            <th width="70px"><a href="#">宝贝序号<img src="/tbgt/img/icons/arrow_down_mini.gif" width="16"
                                                                  height="16" align="absmiddle"/></a></th>
                            <th><a href="#">名称</a></th>
                            <th><a href="#">标题</a></th>
                            <th width="60px"><a href="#">淘宝链接</a></th>
                            <th width="120px"><a href="#">上架时间</a></th>
                            <th width="50px"><a href="#">采购价</a></th>
                            <th width="50px"><a href="#">推荐价</a></th>
                            <th width="60px"><a href="#">实际卖价</a></th>
                            <th width="60px"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${baobeis}" var="baobei">

                            <tr>
                                <td class="a-center">${baobei.id}</td>
                                <td><a href="#">${baobei.name}</a></td>
                                <td>${baobei.saleTitle}</td>
                                <td class="a-center"><a href="${baobei.taobaoLink}">点击进入</a></td>
                                <td>${baobei.incomingTime}</td>
                                <td class="a-right">${baobei.price.purchasePrice}</td>
                                <td class="a-right">${baobei.price.recommendedPrice}</td>
                                <td class="a-right">${baobei.price.salePrice}</td>
                                <td>
                                    <a href="#"><img src="/tbgt/img/icons/order.jpg" width="16" height="16" alt="添加订单"/></a>
                                    <a href="#" onclick="saveBaobei('updateBaobei','${baobei.id}');return false"><img src="/tbgt/img/icons/edit.png" width="16" height="16" alt="修改宝贝"/></a>
                                    <a href="#" onclick="deleteBaobei('${baobei.id}');return false"><img src="/tbgt/img/icons/delete.png" width="16" height="16"
                                                     alt="删除宝贝"/></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <br/>

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

<script type="text/javascript">
    function saveBaobei(action,baobeiId) {
        var dialog = $('<div style="display:none"></div>').appendTo('body');
        // load remote content
        var url = "/tbgt/baobei/"+action+".html";
        if(baobeiId){
            url= url+"?id="+baobeiId;
        }
        dialog.load(
                url,
                function (responseText, textStatus, XMLHttpRequest) {
                    dialog.dialog({
                        title: "添加宝贝",
                        modal: true,
                        width: 800,
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


    function deleteBaobei(baobeiId){
        if(confirm("确认要删除该宝贝吗?")){
            var form = $("<form></form>");
            form.attr('action', "/tbgt/baobei/deleteBaobei.html?id=" + baobeiId);
            form.attr('method', 'post');
            form.appendTo("body");
            form.css('display', 'none');
            form.submit()
        }
    }
</script>