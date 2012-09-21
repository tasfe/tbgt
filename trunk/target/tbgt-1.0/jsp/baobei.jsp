<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TBGT</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<fieldset>
		<legend>Add Baobei Form</legend>
		<center>
		<form:form commandName="baobei" action="/tbgt/baobei/add.html" name="baobeiForm">
		
		<form:hidden path="id"/>
		<table>
			<tr><td colspan="2" align="left"><form:errors path="*" cssStyle="color : red;"/></td></tr>
			<tr><td>Name : </td><td><form:input path="name" /></td></tr>
			<tr><td>Sale Title : </td><td><form:input path="saleTitle" /></td></tr>
			<tr><td>Taobao Link : </td><td><form:input path="taobaoLink" /></td></tr>
			<tr><td>incomingTime : </td><td><form:input path="incomingTime"/></td></tr>
			<tr><td>purchasePrice : </td><td><form:input path="price.purchasePrice"/></td></tr>
            <tr><td>recommendedPrice : </td><td><form:input path="price.recommendedPrice"/></td></tr>
			<tr><td>salePrice : </td><td><form:input path="price.salePrice"/></td></tr>
			<tr><td colspan="2"><input type="submit" value="Add"/>
		</table>
		</form:form>
		</center>
		</fieldset>
		<c:if test="${!empty baobeis}">
		
			<br />
			<center>
				<table width="90%">
					<tr style="background-color: gray;">
						<th>Name</th>
						<th>Sale Title</th>
						<th>Taobao Link</th>
						<th>incomingTime</th>
						<th>purchasePrice</th>
						<th>recommendedPrice</th>
						<th>salePrice</th>
					</tr>
					<c:forEach items="${baobeis}" var="baobei">
						<tr style="background-color: silver;" id="${baobei.id}">
							<td><c:out value="${baobei.name}"/></td>
							<td><c:out value="${baobei.saleTitle}"/></td>
							<td><c:out value="${baobei.taobaoLink}"/></td>
							<td><c:out value="${baobei.incomingTime}"/></td>
							<td><c:out value="${baobei.price.purchasePrice}"/></td>
							<td><c:out value="${baobei.price.recommendedPrice}"/></td>
							<td><c:out value="${baobei.price.salePrice}"/></td>
						</tr>
					</c:forEach>
				</table>
				</center>
			<br />
		
		</c:if>
	</body>
</html>