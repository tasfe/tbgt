<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                      <div><form:errors path="*" cssStyle="color : red;"/></div>
                        <fieldset id="info">
                           <legend>基本信息</legend> 
                            <table width="100%">
                            <tr>
                                <td width="100px">宝贝ID</td><td><c:out value="${baobei.id}"/></td>
                            </tr>
                            <tr>
                                <td width="100px">标题</td><td><c:out value="${baobei.title}"/></td>
                            </tr>    
                            <tr>
                                <td width="100px">上架时间</td><td><fmt:formatDate value="${baobei.list_time}" type="date"
                                                                 pattern="yyyy-MM-dd"/></td>
                            </tr>
                        </table>
                        </fieldset>


                        <table width="100%">
                            <thead>
                            <tr>
                                <th>宝贝属性</th>
                                <th width="60px">售价</th>
                                <th width="50px">重量</th>
                                <th width="60px">采购价</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${baobei.formatSkus}" var="sku" varStatus="index">
                                <tr>
                                    <td class="a-left">
                                        ${sku.properties_name}
                                    </td>
                                    <td class="a-right">
                                        ${sku.price}
                                    </td>
                                    <td><form:input path="skus[${index.index}].weight"/></td>
                                    <td><form:input path="skus[${index.index}].purchase_price"/></td>
                                    <form:hidden path="skus[${index.index}].sku_id"/>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>


                      <div align="center">
                      <input id="saveBtn" type="submit" value="提交" />
                      <input id="resetBtn" type="reset" value="重置"/>
                      </div>
                    </form:form>

                </div>
            </div>
</body>
</html>
<script type="text/javascript">

</script>