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
    <title>易家生活坊宝贝跟踪 物流跟踪信息</title>
</head>

<body>
<div id="dialog_content">
    <div id="box">
                <table width="80%">
                            <c:forEach items="${expSttsLst}" var="stts" varStatus="index">
                                <tr>
                                    <td class="a-left">
                                        ${stts.statusTime}
                                    </td>
                                    <td class="a-left">
                                        ${stts.statusDesc}
                                    </td>
                                </tr>
                            </c:forEach>

               </table>
    </div>
</div>
</body>
</html>