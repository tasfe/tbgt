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

                        <button id="fastUpdateBtn">批量更新</button>
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
                                        <form:hidden path="skus[${index.index}].price" cssClass="price"/>${sku.price}
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
                        <div id="fastUpdate" title="批量更新">
                        </div>
                </div>
            </div>
</body>
</html>
<script type="text/javascript">
    var priceArray={};
    $(function() {

        $(".price").each(function(index,ele){
          if(!priceArray[ele.value])
             priceArray[ele.value] = {};
          priceArray[ele.value][ele.id]=ele.id;
        }
        );
//        console.log(priceArray);
        $.each(priceArray,function(index,ele){
//            console.log(index);
//            console.log(ele);
            var label = $("<label for='"+index+"'>售价 "+index+" 采购价 :  </label>&nbsp;&nbsp");
            var input = $("<input name='"+index+"' id='"+index+"'/> 重量 ： <input name='"+index+"_weight' id='"+index+"_weight'/>");
            $("#fastUpdate").append(label);
            $("#fastUpdate").append(input);
            $("#fastUpdate").append($("<br/>"));
        });
//        $("#fastUpdatePrice").append($("<input type='button' value='更新采购价' onclick='updatePrice()'/>"));

         $( "#fastUpdate" ).dialog({
            autoOpen: false,
            height: 400,
            width: 600,
            modal: true,
            buttons: {
                "批量更新": function() {
                    $.each(priceArray, function(index, ele) {
                        //jquery not support this ele whose id has .
                        //               var value = $("#"+index).val();
                        var p_price_value = document.getElementById(index).value;
                        var weight_value = document.getElementById(index + "_weight").value;
                        $.each(priceArray[index], function(index, ele) {
                            var p_price = index.replace("price", "purchase_price");
                            var weight = index.replace("price", "weight");
                            document.getElementById(p_price).value = p_price_value;
                            document.getElementById(weight).value = weight_value;
                        });

                    });
                    $( this ).dialog( "close" );
                }
            },
            close: function() {
            }
        });

       $( "#fastUpdateBtn" )
            .button()
            .click(function() {
                $( "#fastUpdate" ).dialog( "open" );
            });
    });
    
    function updatePrice(){

        }

</script>