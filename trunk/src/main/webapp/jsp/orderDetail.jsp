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
                <%--<label for="contactPerson">收货人 </label>--%>
                <%--<form:input path="contactPerson" id="contactPerson"/>--%>
                <%--<br/>--%>
                <%--<label for="phone">联系方式 </label>--%>
                <%--<form:input path="phone" id="phone"/>--%>
                <%--<br/>--%>

                <label for="actualPrice">卖出价格 </label>
                <form:input path="actualPrice" id="actualPrice"/>
                <br/>
                <label for="actualPrice">代理费 </label>
                <form:input path="agencyFee" id="agencyFee"/>
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
                        <td><form:input path="soldBaobeis[${index.index}].quantity" onchange="doGenActualPrice('${soldBaobei.salePrice}',this.value,'${index.index}')"/>
                            <input type="hidden" name="oldQuantity${index.index}" id="oldQuantity${index.index}" value="${soldBaobei.quantity}"/></td>
                        <form:hidden path="soldBaobeis[${index.index}].salePrice"/>
                        <form:hidden path="soldBaobeis[${index.index}].baobeiId"/>
                        <form:hidden path="soldBaobeis[${index.index}].purchasePrice"/>
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

        //set default value for actualPrice & agencyFee
        var actualPrice = 0;
        <c:forEach items="${order.soldBaobeis}" var="soldBaobei" varStatus="index">
                actualPrice=actualPrice+${soldBaobei.salePrice} * ${soldBaobei.quantity}
        </c:forEach>
        $("#actualPrice").attr("value",actualPrice);

        $("#agencyFee").attr("value",5.0);


    });

    $('#form').submit(function() {
        var options = {
//            target:'#message',
            url:'/tbgt/order/save.html',
            type:'POST',
            success: function() {
                $(orderDialog).dialog('close');
                jSuccess(
                    '订单添加成功',
                    {
                        autoHide : true, // added in v2.0
                        clickOverlay : false, // added in v2.0
                        MinWidth : 400,
                        TimeShown : 2000,
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
        };

        $(this).ajaxSubmit(options);
        return false;
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

    function doGenActualPrice(soldPrice,quantity,index){
        var currentTotal =  parseFloat($("#actualPrice").val());
        var diffQuantity =  parseInt(quantity) - parseInt($("#oldQuantity"+index).val());
        $("#actualPrice").val((currentTotal+parseFloat(soldPrice*diffQuantity)).toFixed(2));
        $("#oldQuantity"+index).val(quantity);
    }
</script>