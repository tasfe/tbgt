<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache" />
    <title>易家生活坊宝贝跟踪 宝贝管理</title>
    <link rel="shortcut icon" href="<tbgt:constant name="ContextPath"/>/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/theme.css"/>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/style.css"/>
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/ie-sucks.css"/>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/custom/jquery-ui-1.8.23.custom.css"/>
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jquery.dataTables.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/datatable_jui.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/EMSwitchBox.css" type="text/css" media="screen" charset="utf-8">
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jNotify.jquery.css" type="text/css" media="screen" charset="utf-8">

    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-ui-1.8.23.custom.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.form.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/EMSwitchBox.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jNotify.jquery.js"></script>

</head>

<body>
<div id="container">
    <div id="header">
        <h2>易家生活坊宝贝跟踪</h2>

        <div id="topmenu">
            <ul>
                <li><a href="<tbgt:constant name="ContextPath"/>/order/view.html">订单管理</a></li>
                <li class="current"><a href="<tbgt:constant name="ContextPath"/>/baobei/view.html">宝贝管理</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html">统计</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/tools/express.html">工具</a></li>
            </ul>
        </div>
    </div>
    <div id="top-panel">
        <div id="panel">
            <ul>
                <li><a href="#" onclick="saveBaobei('newBaobei');return false" class="add">添加新宝贝</a></li>
                <li><a href="#" onclick="batchOrderBaobei();return false" class="order">添加订单</a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <div id="content">
            <div id="box">
                <h3>宝贝列表</h3> <br/>
                    <table id="tdata1" class="display" cellspacing="0" cellpadding="0" border="0" width="100%">
                        <thead>
                        <tr>
                            <th width="70px">序号</th>
                            <th>名称</th>
                            <th>标题</th>
                            <th width="60px" nowrap>重量(公斤)</th>
                            <th width="120px" nowrap>上架时间</th>
                            <th width="50px" nowrap>采购价</th>
                            <th width="50px" nowrap>推荐价</th>
                            <th width="60px" nowrap>实际卖价</th>
                            <th width="60px" nowrap>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                          <td colspan="9" class="dataTables_empty">加载数据.....</td>
                        </tbody>
                    </table>
            </div>
            <br/>

        </div>

    </div>
    <div id="footer">
        <div id="credits">
            版权所有 © 2012-2014, 易家生活坊, All Rights Reserved.
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    function saveBaobei(action,baobeiId) {
        var dialog = $('<div style="display:none"></div>').appendTo('body');
        // load remote content
        var url = "<tbgt:constant name="ContextPath"/>/baobei/"+action+".html";
        if(baobeiId){
            url= url+"?id="+baobeiId;
        }
        dialog.load(
                url,
                function (responseText, textStatus, XMLHttpRequest) {
                    dialog.dialog({
                        title: "宝贝详情",
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

    function batchOrderBaobei(){
        var  baobeiIds;
        for(var index in gaiSelected){
            if(baobeiIds && baobeiIds!=null){
               baobeiIds = baobeiIds + "," + gaiSelected[index];
            }else{
              baobeiIds= gaiSelected[index];
            }
        }
        if(baobeiIds==null){
            alert("没有宝贝被选择");
            return false;
        }
        return orderBaobei(baobeiIds);
    }


    function deleteBaobei(baobeiId){
        if(confirm("确认要删除该宝贝吗?")){
            var form = $("<form></form>");
            form.attr('action', "<tbgt:constant name="ContextPath"/>/baobei/deleteBaobei.html?id=" + baobeiId);
            form.attr('method', 'post');
            form.appendTo("body");
            form.css('display', 'none');
            form.submit()
        }
    }
     var orderDialog;
    function orderBaobei(baobeiId){
        if (jQuery.inArray(baobeiId, gaiSelected) == -1) {
            gaiSelected[gaiSelected.length++] = baobeiId;
        }
        else {
            gaiSelected = jQuery.grep(gaiSelected, function(value) {
                return value != baobeiId;
            });
        }
        $(this).toggleClass('row_selected');
        return orderBaobeiDialog(baobeiId);
    }

    function orderBaobeiDialog(baobeiIds){
        orderDialog = $('<div style="display:none"></div>').appendTo('body');
        // load remote content
        var url = "<tbgt:constant name="ContextPath"/>/order/addOrder.html?baobeiIdsStr="+baobeiIds;
        orderDialog.load(
                url,
                function (responseText, textStatus, XMLHttpRequest) {
                    orderDialog.dialog({
                        title: "添加订单",
                        modal: true,
                        width: 800,
                        height: 600,
                        close: function(event, ui) {
                            orderDialog.remove();
                        }
                    });
                }
        );
        //prevent the browser to follow the link
        return false;
    }

    var tdata1;
    var gaiSelected = [];
    $(document).ready(function() {


        tdata1 = $('#tdata1').dataTable({
            "sPaginationType": "full_numbers",
            "bJQueryUI": true,
            'iDisplayLength': 50,
            "aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "bStateSave": true,
            "oLanguage": {
                "sProcessing": "系统处理中",
                "sLoadingRecords": "请等待，数据载入中.....",
                "sLengthMenu": "每页显示 _MENU_ 记录",
                "sInfo": "第 _START_ 到 _END_ 条, 总共 _TOTAL_ 条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索名称或者标题 " ,
                "sZeroRecords": "没有宝贝",
                "sInfoEmpty": "没有宝贝",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sLast": "最后一页",
                    "sNext": "下一页",
                    "sPrevious": "上一页"
                }
            },
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": "<tbgt:constant name="ContextPath"/>/baobei/list.html",
            "aoColumns": [
                { "mData": "price.baobeiId" },
                { "mData": "name" },
                { "mData": "saleTitle" },
                { "mData": "weight" },
                { "mData": "incomingTime" },
                { "mData": "price.purchasePrice" },
                { "mData": "price.recommendedPrice" },
                { "mData": "price.salePrice" },
                { "mData": null }
            ],
            "aoColumnDefs": [
                { "bSortable" :true, "aTargets": [ 0 ],"sWidth": "70px"},
                { "bSortable" :true, "aTargets": [ 1 ],  "sWidth": "150px"},
                { "bSortable" :true, "aTargets": [ 2 ],  "sWidth": "260px"},
                { "bSortable": false, "aTargets": [ 3 ],  "sWidth": "100px" ,"sClass":"nowrap  a-right"},
                { "bSortable": true, "aTargets": [ 4 ],  "sWidth": "120px" },
                { "bSortable": true, "aTargets": [ 5 ],  "sWidth": "70px","sClass":"nowrap a-right" },
                { "bSortable": true, "aTargets": [ 6 ],  "sWidth": "70px","sClass":"nowrap  a-right" },
                { "bSortable": true, "aTargets": [ 7 ],  "sWidth": "80px ,","sClass":"nowrap  a-right" },
                { "bSortable": false, "aTargets": [ 8 ],  "sWidth": "80px" }
            ],
            "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                    $('td:eq(2)', nRow).html('<a target="_blank" href="'+aData.taobaoLink+' ">'+aData.saleTitle+'</a>');
                    $('td:eq(8)', nRow).html('<a href="#" title="修改宝贝" onclick="saveBaobei(\'updateBaobei\',\''+aData.id+'\');return false"><img src="<tbgt:constant name="ContextPath"/>/images/icons/edit.png" width="16" height="16" alt="修改宝贝"/></a><a href="#" onclick="orderBaobei(\''+aData.id+'\');return false" title="添加订单"><img src="<tbgt:constant name="ContextPath"/>/images/icons/order.jpg" width="16" height="16" alt="添加订单"/></a> <a href="#" onclick="deleteBaobei(\''+aData.id+'\');return false" title="删除宝贝"><img src="<tbgt:constant name="ContextPath"/>/images/icons/delete.png" width="16" height="16" alt="删除宝贝"/></a>');
                if (jQuery.inArray(aData.id, gaiSelected) !== -1) {
                    $(nRow).addClass('row_selected');
                }
            }

        });

        /* Click event handler */
        $('#tdata1 tbody tr').live('click', function () {
            var aData = tdata1.fnGetData(this);
            var id = aData.id;

            if (jQuery.inArray(id, gaiSelected) == -1) {
                gaiSelected[gaiSelected.length++] = id;
            }
            else {
                gaiSelected = jQuery.grep(gaiSelected, function(value) {
                    return value != id;
                });
            }
//            console.log(gaiSelected);
            $(this).toggleClass('row_selected');
        });


        $("#tdata1 tbody tr").live('dblclick', function () {
            saveBaobei('updateBaobei',tdata1.fnGetData(this).id);
        });


    });
</script>