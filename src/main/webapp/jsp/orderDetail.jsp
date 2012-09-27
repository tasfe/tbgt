<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>易家生活坊宝贝跟踪 订单详情</title>
    <script type="text/javascript" src="/tbgt/js/EMSwitchBox.js"></script>
    <link rel="stylesheet" href="/tbgt/css/EMSwitchBox.css" type="text/css" media="screen" charset="utf-8">
</head>

<body>
<div id="dialog_content">
    <div id="box">
        <form:form commandName="order" action="/tbgt/order/save.html" id="form">
            <div><form:errors path="*" cssStyle="color : red;"/></div>
            <div>
                <input type="checkbox" name="newOrderInd" value="15">
            </div>
            <fieldset id="newOrder" style="display:none">
                <legend>基本信息</legend>
                <label for="orderNo">订单号 </label>
                <form:input path="orderNo" id="orderNo"/>
                <br/>
                <label for="address">地址 </label>
                <form:input path="address" id="address"/>
                <br/>
                <label for="contactPerson">收货人 </label>
                <form:input path="contactPerson" id="contactPerson"/>
                <br/>
                <label for="phone">联系方式 </label>
                <form:input path="phone" id="phone"/>
                <br/>

                <label for="actualPrice">卖出价格 </label>
                <form:input path="actualPrice" id="actualPrice"/>
                <br/>

                <label for="soldTime">成交时间 </label>
                <form:input path="soldTime" id="soldTime" class="date"/>
                <br/>

            </fieldset>

            <div id="existingOrder" style="display:none">
                已有订单 : <input id="existingOrderNo">
            </div>

            <table width="100%">
                <thead>
                <tr>
                    <th width="200px"><a href="#">宝贝名称</a></th>
                    <th width="150px"><a href="#">颜色</a></th>
                    <th width="100px"><a href="#">规格</a></th>
                    <th width="100px"><a href="#">数量</a></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.soldBaobeis}" var="soldBaobei" varStatus="index">
                    <tr>
                        <td class="a-left">
                            <form:hidden path="soldBaobeis[${index.index}].name"/>${soldBaobei.name}
                        </td>
                        <td><form:input path="soldBaobeis[${index.index}].color"/></td>
                        <td><form:input path="soldBaobeis[${index.index}].spec"/></td>
                        <td><form:input path="soldBaobeis[${index.index}].quantity" onchange="doGenActualPrice(${soldBaobei.salePrice},this.value)"/></td>
                        <form:hidden path="soldBaobeis[${index.index}].salePrice"/>
                        <form:hidden path="soldBaobeis[${index.index}].baobeiId"/>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <div align="center">
                <input id="saveBtn" type="submit" value="提交"/>
                <input id="resetBtn" type="reset" value="重置"/>
            </div>
        </form:form>

    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function() {
        $('input[type=checkbox]').EMSwitchBox({onLabel : '新建', offLabel : '合并'});
        $('div.switch').click();

        $(".date").datepicker({ dateFormat: "yy-mm-dd"});
        $(".date").datepicker("setDate", new Date());

        var availableOrders = [
            "张三",
            "李四",
            "王二",
            "麻子",
            "123456789",
            "123456780",
            "123456788",
            "123456787",
            "123456786",
            "123456785"
        ];
        $("#existingOrderNo").autocomplete({
                    source: availableOrders

                });

        var actualPrice = 0;
        <c:forEach items="${order.soldBaobeis}" var="soldBaobei" varStatus="index">
                actualPrice=actualPrice+${soldBaobei.salePrice} * ${soldBaobei.quantity}
        </c:forEach>
        $("#actualPrice").attr("value",actualPrice);
    });

    function EMSwitchBoxCallback(checkbox) {
        if ($(checkbox).attr('checked')) {
            $("#existingOrder").hide();
            $("#newOrder").show();
        } else {
            $("#existingOrder").show();
            $("#newOrder").hide();
        }
    }

    function doGenActualPrice(soldPrice,quantity){
        $("#actualPrice").attr("value",soldPrice*quantity);
    }
</script>