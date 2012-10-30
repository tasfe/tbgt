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
                                        <form:hidden path="skus[${index.index}].properties_name"/>${sku.properties_name}
                                    </td>
                                    <td class="a-right">
                                        <form:hidden path="skus[${index.index}].price"/>${sku.price}
                                    </td>
                                    <td><form:input path="skus[${index.index}].weight"/></td>
                                    <td><form:input path="skus[${index.index}].purchase_price" cssClass="purchase_price"/></td>
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
    var skus_props = {};

    var skus_props_e = {};
    var fastUpdate = $("<div id=\"fastUpdate\" title=\"批量更新\"></div>").appendTo($("#box"));
    var fastUpdateTable = $("<table></table>").appendTo(fastUpdate);
    fastUpdateTable.append("<tr><td colspan=\"9\" align=\"right\"><a href=\"#\" onclick=\"add();return false\"><img src=\"<tbgt:constant name="ContextPath"/>/images/icons/add.png\"/></a></td></tr>")
        <c:forEach items="${baobei.skus_props}" var="sp" varStatus="index">
           skus_props['${sp.key}']=[];
           skus_props_e['${sp.key}']='prop${index.index}';
            <c:forEach items="${sp.value}" var="spv" varStatus="index">
                skus_props['${sp.key}'][${index.index}] = '${spv}';
            </c:forEach>
        </c:forEach>
        $(function() {
//            add();

            fastUpdate.dialog({
                autoOpen: false,
                height: 400,
                width: 1000,
                modal: true,
                buttons: {
                    "批量更新": function() {
                    var update_info_map = {};
                    $.each($(".updateInfo"), function(index, ele) {
                         var update_info_key = "";
                         $.each($(ele).find(".sku_props"), function(index, ele) {
                             if(ele.value!=null && ele.value!=''){
                                if(update_info_key!="")update_info_key = update_info_key+", ";
                                update_info_key=update_info_key+ele.name+": "+ele.value;
                             }
                         });
                        console.log(update_info_key);
                        if(update_info_key=="")return;
                        update_info_map[update_info_key]={};
                         $.each($(ele).find(".price"), function(index, ele) {
                             update_info_map[update_info_key]['price']=ele.value;
                         });
                         $.each($(ele).find(".weight"), function(index, ele) {
                             update_info_map[update_info_key]['weight']=ele.value;
                         });
//                         console.log(update_info_map);
                         $.each($(".purchase_price"), function(index, ele) {
                            var purchase_price_id = ele.id;
                            var properties_name_id = purchase_price_id.replace("purchase_price", "properties_name");
                            var properties_name = document.getElementById(properties_name_id).value;
                            var weight_id = purchase_price_id.replace("purchase_price", "weight");
//                            console.log(properties_name);
                             //jquery not support this ele whose id has .
                        //               var value = $("#"+index).val();
                        var purchase_price_v="";
                        var weight_v="";
                        console.log(update_info_map[properties_name]);
                        if(typeof update_info_map[properties_name] != 'undefined'){
                            purchase_price_v = update_info_map[properties_name]['price'];
                            weight_v = update_info_map[properties_name]['weight'];
                        }else{
                            var props = properties_name.split(", ");
                            $.each(props, function(index, value) {

                                if(typeof update_info_map[value] != 'undefined'){
                                     purchase_price_v = update_info_map[value]['price'];
                                     weight_v = update_info_map[value]['weight'];
                                     return false;
                                }
                            });
                        }
                        if(purchase_price_v!=""){
                          document.getElementById(purchase_price_id).value = purchase_price_v;
                        }
                        if(weight_v!=""){
                          document.getElementById(weight_id).value = weight_v;
                        }

                         });

                    });

                     $( this ).dialog( "close" );
                    }
                },
                close: function() {
//                    fastUpdate.remove();
                }
            });

           $("#fastUpdateBtn").button().click(function() {
                    fastUpdate.dialog( "open" );
                });
        });
     function add(){
         var fastUpdateTr = $("<tr class=\"updateInfo\"></tr>").appendTo(fastUpdateTable);
                   $.each(skus_props,function(key,value){
                      var fastUpdateTd = $("<td>"+key+"</td><td><input class='"+skus_props_e[key]+" sku_props' name='"+key+"'></td>");
                       fastUpdateTr.append(fastUpdateTd);
                       $("."+skus_props_e[key]).autocomplete({
                               minLength: 0,
                               source: skus_props[key],
                               create: function(event, ui) {
                                   $(this).bind("click focus", function() {
                                       var active = $(this).data("autocomplete").menu.active; //没有这一行，鼠标选择选项时，会触发输入的click事件，导致提示框不能关闭
                                       if (!active) {
                                           $(this).autocomplete("search", "");
                                       }
                                   });
                               }
                           });
                   });
                   fastUpdateTr.append("<td>成本价： </td><td><input class=\"price\"></td><td>重量： </td><td><input class=\"weight\"></td><td ><a href=\"#\" onclick=\"deleteRow(this);return false\"><img src=\"<tbgt:constant name="ContextPath"/>/images/icons/delete.png\"/></a></td>");
     }
     function deleteRow(td){
        $(td).parent().parent().remove();
     }
</script>