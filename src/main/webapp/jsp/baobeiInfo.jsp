<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<title>易家生活坊宝贝跟踪 添加宝贝</title>
</head>

<body>
            <div id="dialog_content">
                <div id="box">
                    <tbgt:constant name='ContextPath' var="ContextPath"/>
                    <form:form commandName="baobei" action="${ContextPath}/baobei/save.html" id="form">
                      <form:hidden path="id"/>
                      <div><form:errors path="*" cssStyle="color : red;"/></div>
                      <fieldset id="info">
                        <legend>基本信息</legend>
                        <label for="name" >名称 </label>
                        <form:input path="name" id="name"/>
                        <br />
                        <label for="saleTitle" >标题 </label>
                        <form:input path="saleTitle" id="saleTitle"/>
                        <br />
                        <label for="weight" >重量 </label>
                        <form:input path="weight" id="weight"/>
                        <br />
                        <label for="taobaoId">淘宝ID </label>
                        <form:input path="taobaoId" id="taobaoId"/>
                        <br />

                        <label for="incomingTime">上架时间 </label>
                        <form:input path="incomingTime" id="incomingTime" class="date"/>
                        <br />
                      </fieldset>
                      <fieldset id="price">
                        <form:hidden path="price.id"/>
                        <legend>价格信息</legend>
                        <label for="purchasePrice" >采购价 </label>
                        <form:input path="price.purchasePrice" id="purchasePrice"/>
                        <br />
                        <label for="recommendedPrice">推荐价 </label>
                        <form:input path="price.recommendedPrice" id="recommendedPrice"/>
                        <br />
                        <label for="salePrice">实际卖价 </label>
                        <form:input path="price.salePrice" id="salePrice"/>
                      </fieldset>
                      <div align="center">
                      <input id="saveBtn" type="submit" value="提交" />
                      <input id="resetBtn" type="reset" value="重置"/>
                      <input id="saveNewBtn" type="button" onclick="saveAsNew()" value="另存为新宝贝" />
                      </div>
                    </form:form>

                </div>
            </div>
</body>
</html>
<script type="text/javascript">
    $(function() {
		$(".date" ).datepicker({ dateFormat: "yy-mm-dd"});
        <c:if test="${baobei.incomingTime ==null}">
            $(".date").datepicker("setDate", new Date());
            $("#agencyFee").attr("value",5.0);
        </c:if>
	});
    function saveAsNew(){
        $('#id').val("");
        $('#form').submit();
    }
</script>