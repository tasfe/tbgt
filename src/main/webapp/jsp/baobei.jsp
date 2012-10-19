<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <li><a href="#" onclick="refresh();return false" class="refresh">同步所有宝贝（不会删除已删除宝贝）</a></li>
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
                            <th width="70px">宝贝ID</th>
                            <th>标题</th>
                            <th width="120px" nowrap>上架时间</th>
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

    function refresh(baobeiid){
        if (baobeiid != null && baobeiid != "") {
            if (confirm("即将同步该宝贝，确认手没抖吗?")) {
                window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/baobei/refreshBaobei.html?bbid="+baobeiid;
            }
            return;
        }
        if (confirm("即将同步所有宝贝，确认手没抖吗?")) {
            window.location.href = "<tbgt:constant name="ContextPath"/>/top/getSessionKey.html?callbackUrl=<tbgt:constant name="ContextPath"/>/baobei/refresh.html";
        }
    }

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
                        width: 1024,
                        height: 768,
                        close: function(event, ui) {
                            dialog.remove();
                        }
                    });
                }
        );
        //prevent the browser to follow the link
        return false;
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
                { "mData": "id" },
                { "mData": "title" },
                { "mData": "list_time" },
                { "mData": null }
            ],
            "aoColumnDefs": [
                { "bSortable" :true, "aTargets": [ 0 ],"sWidth": "150px","sClass":"a-center"},
                { "bSortable" :true, "aTargets": [ 1 ]},
                { "bSortable" :true, "aTargets": [ 2 ],  "sWidth": "160px","sClass":"a-center"},
                { "bSortable": false, "aTargets": [ 3 ],  "sWidth": "80px" ,"sClass":"a-center"}
            ],
            "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                    $('td:eq(1)', nRow).html('<div><a target="_blank" href="'+aData.detail_url+' "><img src="'+aData.pic_url+'" class="item-pic"></a></div>'+'<div><a target="_blank" href="'+aData.detail_url+' ">'+aData.title+'</a></div>');
                    $('td:eq(3)', nRow).html('<a href="#" title="同步宝贝" onclick="refresh(\''+aData.id+'\');return false"><img src="<tbgt:constant name="ContextPath"/>/images/icons/refresh.png" width="16" height="16" alt="同步宝贝"/></a> <a href="#" onclick="deleteBaobei(\''+aData.id+'\');return false" title="删除宝贝"><img src="<tbgt:constant name="ContextPath"/>/images/icons/delete.png" width="16" height="16" alt="删除宝贝"/></a>');
                if (jQuery.inArray(aData.id, gaiSelected) !== -1) {
                    $(nRow).addClass('row_selected');
                }
            }

        });


        $("#tdata1 tbody tr").live('dblclick', function () {
            saveBaobei('updateBaobei',tdata1.fnGetData(this).id);
        });


    });
</script>