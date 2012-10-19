<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>易家生活坊宝贝跟踪 快递信息</title>
</head>

<body>
<div id="dialog_content">
    <div id="box">
        <tbgt:constant name='ContextPath' var="ContextPath"/>
        <form:form commandName="express" action="${ContextPath}/order/saveExpress.html" id="form" method="post">
            <form:hidden path="id"/>
            <form:hidden path="orderId"/>
            <div><form:errors path="*" cssStyle="color : red;"/></div>
            <fieldset id="info">
                <legend>快递信息</legend>
                <label for="expressNo">单号 </label>
                <form:input path="expressNo" id="expressNo" onchange="getExpressFee(this.value)"/>
                <br/>
                <label for="fee">费用 </label>
                <form:input path="fee" id="fee"/>
                <br/>
                <label for="giftFee">小礼物费用 </label>
                <form:input path="giftFee" id="giftFee"/>
                <br/>
                <label for="sendTime">时间 </label>
                <form:input path="sendTime" id="sendTime" class="date"/>
                <br/>
            </fieldset>
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
        $(".date").datepicker({ dateFormat: "yy-mm-dd"});
        $(".date").datepicker("setDate", new Date());
    });
    function getExpressFee(expressNo){
        if($("#fee").val()=="" || $("#fee").val()=="0"){
            $.ajax({
                type: "post",
                url: '<tbgt:constant name='ContextPath'/>/order/getExpressFee.html?expressNo='+expressNo+"&orderId="+$("#orderId").val(),
                beforeSend: function(XMLHttpRequest) {
                    //ShowLoading();
                },
                success: function(data, textStatus) {
                    $("#fee").val(data);
                },
                complete: function(XMLHttpRequest, textStatus) {
                    //HideLoading();
                },
                error: function() {
                    //请求出错处理
                }
            });
        }

    }
</script>