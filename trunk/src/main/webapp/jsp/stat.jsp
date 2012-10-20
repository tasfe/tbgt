<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/tbgt.tld" prefix="tbgt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>易家生活坊宝贝跟踪 统计</title>
    <link rel="shortcut icon" href="<tbgt:constant name="ContextPath"/>/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/theme.css"/>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/style.css"/>
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name=" ContextPath"/>/css/ie-sucks.css" />
    <![endif]-->

    <link rel="stylesheet" type="text/css"
          href="<tbgt:constant name="ContextPath"/>/css/custom/jquery-ui-1.8.23.custom.css"/>
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/jquery.dataTables.css" type="text/css"
          media="screen, projection">
    <link rel="stylesheet" href="<tbgt:constant name="ContextPath"/>/css/datatable_jui.css" type="text/css"
          media="screen, projection">
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery-ui-1.8.23.custom.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/jquery.dataTables.min.js"></script>

    <!--[if lt IE 9]>
    <script language="javascript" type="text/javascript" src="<tbgt:constant name="
            ContextPath"/>/js/excanvas.min.js"></script><![endif]-->
    <script language="javascript" type="text/javascript"
            src="<tbgt:constant name="ContextPath"/>/js/jquery.jqplot.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/jquery.jqplot.min.css"/>
    <script class="include" language="javascript" type="text/javascript"
            src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.dateAxisRenderer.min.js"></script>
    <script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.cursor.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.highlighter.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.dragable.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.trendline.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.pointLabels.min.js"></script>
<script type="text/javascript" src="<tbgt:constant name="ContextPath"/>/js/plugins/jqplot.json2.min.js"></script>
    <style type="text/css">
        #panel .current a, #panel .current a:hover, #panel .current a:visited {
            padding: 5px 12px 5px 20px;
            font-weight: bold;
        }
    </style>
</head>

<body>
<div id="container">
    <div id="header">
        <h2>易家生活坊宝贝跟踪</h2>

        <div id="topmenu">
            <ul>
                <li><a href="<tbgt:constant name="ContextPath"/>/order/view.html">订单管理</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/baobei/view.html">宝贝管理</a></li>
                <li class="current"><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html">统计</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/tools/express.html">工具</a></li>
            </ul>
        </div>
    </div>
    <div id="top-panel">
        <div id="panel">
            <ul>
                <li id="profitLi" class="current"><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html" class="profit">利润统计</a></li>
                <li id="chartLi"><a href="#" class="volume" onclick="chart()">统计图表</a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <div id="content">
            <div id="box">
                <h3>利润</h3>
                <tbgt:constant name='ContextPath' var="ContextPath"/>
                <form:form action="${ContextPath}/stat/profit.html" commandName="criteria" id="form">
                    <table width="100%">
                        <tr>
                            <th class="a-left">
                                从&nbsp;
                                <form:input path="fromDate" id="fromDate" class="date"/>
                                &nbsp;到&nbsp;
                                <form:input path="toDate" id="toDate" class="date"/>
                                &nbsp;
                                宝贝名
                                <form:input path="name" id="name"/>
                                &nbsp;
                                <input id="saveBtn" type="submit" value="搜索" class="button"/>
                            </th>
                        </tr>

                    </table>
                </form:form>
                <br/>

                <table width="100%">
                    <tr>
                        <td width="200px" class="f_name">总销售额</td>
                        <td>${summary.totalSale}</td>
                        <td width="200px" class="f_name">总代理费</td>
                        <td>${summary.totalAgencyFee}</td>
                    </tr>
                    <tr>
                        <td width="200px" class="f_name">总成本</td>
                        <td>${summary.totalPurchase}</td>
                        <td width="200px" class="f_name">总快递费</td>
                        <td>${summary.totalExpress}</td>
                    </tr>
                    <tr>
                        <td width="70px" class="f_name">总利润</td>
                        <td>${summary.totalProfit}</td>
                        <td width="70px" class="f_name">总应付款</td>
                        <td>${summary.totalPaid}</td>
                    </tr>

                </table>

                <br/>
                <table id="tdata1" class="display" cellspacing="0" cellpadding="0" border="0" width="100%">
                    <thead>
                    <tr>
                        <th width="70px">订单号</th>
                        <th>订单详情</th>
                        <th width="100px">成交时间</th>
                        <th width="60px">成交价</th>
                        <th width="60px">成本</th>
                        <th width="80px">快递费用</th>
                        <th width="80px">小礼物费用</th>
                        <th width="60px">代理费</th>
                        <th width="60px">利润</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order" varStatus="index">
                        <tr>
                            <td class="a-center">${order.id}</td>
                            <td>
                                    ${order.receiver_address}<br/>
                                <c:forEach items="${order.soldBaobeis}" var="soldBaobei">
                                    ${soldBaobei.quantity} 个 ${soldBaobei.title} <br/>
                                </c:forEach>
                            </td>
                            <td class="a-center"><fmt:formatDate value="${order.pay_time}" type="date"
                                                                 pattern="yyyy-MM-dd"/></td>
                            <td class="a-right">${order.actualPrice}</td>
                            <td class="a-right">${order.purchasePrice}</td>
                            <td class="a-right">${order.express.fee}</td>
                            <td class="a-right">${order.express.giftFee}</td>
                            <td class="a-right">${order.express.agencyFee}</td>
                            <td class="a-right">${order.profit}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <br/>
        </div>

        <div id="tabs" style="display:none;margin: 10px 0 10px 0;padding: 10px;background: white;width:960px;clear: both;">
            <ul>
                <li><a href="#tabs-1">最近7天</a></li>
                <li><a href="#tabs-2">最近30天</a></li>
            </ul>
            <div id="tabs-1">
                <div id="chart1" style="height:300px;width:900px;"></div>
            </div>

            <div id="tabs-2">
                 <div id="chart2" style="height:300px;width:900px;"></div>
            </div>
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
    var tdata1;
    jQuery.extend(jQuery.fn.dataTableExt.oSort, {
        "num-html-pre": function (a) {
            var x = a.replace(/<.*?>/g, "");
            return parseFloat(x);
        },

        "num-html-asc": function (a, b) {
            return ((a < b) ? -1 : ((a > b) ? 1 : 0));
        },

        "num-html-desc": function (a, b) {
            return ((a < b) ? 1 : ((a > b) ? -1 : 0));
        }
    });

    $(function() {
        $(".date").datepicker({ dateFormat: "yy-mm-dd"});
        //datatable
        tdata1 = $('#tdata1').dataTable({
            "sPaginationType": "full_numbers",
            "bJQueryUI": true,
            'iDisplayLength': 50,
            "aLengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
//            "bSort": false,
            "oLanguage": {
                "sLengthMenu": "每页显示 _MENU_ 记录",
                "sInfo": "第 _START_ 到 _END_ 条, 总共 _TOTAL_ 条记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "全局搜索" ,
                "sZeroRecords": "没有订单",
                "sInfoEmpty": "",
                "oPaginate": {
                    "sFirst": "第一页",
                    "sLast": "最后一页",
                    "sNext": "下一页",
                    "sPrevious": "上一页"
                }
            },
            "aaSorting": [
                [ 2, "desc" ]
            ]
        });


    });
    var plot1=null;
    var plot2=null;
    function chart() {
//        $.jqplot.config.enablePlugins = true;
        $("#profitLi").removeClass("current");
        $("#chartLi").addClass("current");
        $("#content").hide();
        $("#tabs").show();
        $("#tabs").tabs();
       
        if(plot1==null) {
            plot1 = createChart('chart1',"<tbgt:constant name='ContextPath'/>/stat/lastweek.html","1 days");
        }
        if(plot2==null)  {
            plot2 = createChart('chart2',"<tbgt:constant name='ContextPath'/>/stat/lastmonth.html","3 days");
        }
        //jqplot
        $('#tabs').bind('tabsshow', function(event, ui) {
            if (ui.index === 0) {
                plot1.replot();
            }
            else if (ui.index === 1) {
                plot2.replot();
            }
        });
    }
    var ajaxDataRenderer = function(url, plot, options) {
        var ret = null;
        $.ajax({
            // have to use synchronous here, else the function
            // will return before the data is fetched
            async: false,
            url: url,
            type: "post",
//            dataType:"json",
            success: function(data) {
                ret = eval("("+data+")");
//                ret = data;
            }
        });
        return [ret];
    };
    function createChart(target,url,dateInterval){
        return $.jqplot(target, url, {
            title:'利润图',
            dataRenderer: ajaxDataRenderer,
            axes:{
                xaxis:{
                    max:new Date(),
                    tickInterval:dateInterval,
                    tickOptions:{
                        angle: -20,
                        formatString:'%m-%d'
                    },
                    tickRenderer:$.jqplot.CanvasAxisTickRenderer,
//                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    renderer:$.jqplot.DateAxisRenderer
                },
                yaxis:{
                    tickOptions: {
                        formatString: '￥%.2f'
                    }
                }

            },
            highlighter: {
                show: true,
                sizeAdjust: 15,
                tooltipLocation: 's',
                tooltipAxes: 'xy',
                useAxesFormatters: true
            },
            cursor: {
                show: false
            },
            animate: true,
            animateReplot: true,
            series:[
                {
                    lineWidth:4,
                    pointLabels: {
                        show: true
                    }
                }
            ]
        });
    }


//    Date.prototype.format = function(format)
//    {
//        /*
//         * format="yyyy-MM-dd hh:mm:ss";
//         */
//        var o = {
//            "M+" : this.getMonth() + 1,
//            "d+" : this.getDate(),
//            "h+" : this.getHours(),
//            "m+" : this.getMinutes(),
//            "s+" : this.getSeconds(),
//            "q+" : Math.floor((this.getMonth() + 3) / 3),
//            "S" : this.getMilliseconds()
//        }
//
//        if (/(y+)/.test(format))
//        {
//            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
//                    - RegExp.$1.length));
//        }
//
//        for (var k in o)
//        {
//            if (new RegExp("(" + k + ")").test(format))
//            {
//                format = format.replace(RegExp.$1, RegExp.$1.length == 1
//                        ? o[k]
//                        : ("00" + o[k]).substr(("" + o[k]).length));
//            }
//        }
//        return format;
//    }
</script>