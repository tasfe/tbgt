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
    <title>易家生活坊宝贝跟踪 工具</title>
    <link rel="shortcut icon" href="<tbgt:constant name="ContextPath"/>/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/theme.css"/>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/style.css"/>
    <!--[if IE]>
    <link rel="stylesheet" type="text/css" href="<tbgt:constant name="ContextPath"/>/css/ie-sucks.css" />
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
</head>

<body>
<div id="container">
    <div id="header">
        <h2>易家生活坊宝贝跟踪</h2>

        <div id="topmenu">
            <ul>
                <li><a href="<tbgt:constant name="ContextPath"/>/order/view.html">订单管理</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/baobei/view.html">宝贝管理</a></li>
                <li><a href="<tbgt:constant name="ContextPath"/>/stat/profit.html">统计</a></li>
                <li class="current"><a href="<tbgt:constant name="ContextPath"/>/tools/express.html">工具</a></li>
            </ul>
        </div>
    </div>
    <div id="top-panel">
        <div id="panel">
            <ul>
                <li><a href="#" class="calc">快递费用计算</a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <div id="content">
            <div id="box">
                <h3>邮资</h3>
                <tbgt:constant name='ContextPath' var="ContextPath"/>
                <form:form action="${ContextPath}/tools/express.html" commandName="expressCodeCriteria" id="form">
                    <table width="100%">
                        <tr>
                            <th class="a-left" width="450px">
                                省份
                                <form:input path="province" id="province"/>
                                &nbsp;
                                重量
                                <form:input path="weight" id="weight"/>
                                &nbsp;
                                <input id="searchBtn" type="submit" value="搜索" class="button"/>
                            </th>
                            <th>
                                <table>
                                    <tr>
                                        <td><a href="#" onclick="popup('北京')">北京</a></td>
                                        <td><a href="#" onclick="popup('安徽省')">安徽省</a></td>
                                        <td><a href="#" onclick="popup('福建省')">福建省</a></td>
                                        <td><a href="#" onclick="popup('甘肃省')">甘肃省</a></td>
                                        <td><a href="#" onclick="popup('内蒙古自治区')">内蒙古自治区</a></td>

                                        <td><a href="#" onclick="popup('澳门特别行政区')">澳门特别行政区</a></td>
                                    </tr>
                                    <tr>
                                        <td><a href="#" onclick="popup('上海')">上海</a></td>
                                        <td><a href="#" onclick="popup('海南省')">海南省</a></td>
                                        <td><a href="#" onclick="popup('河北省')">河北省</a></td>
                                        <td><a href="#" onclick="popup('河南省')">河南省</a></td>
                                        <td><a href="#" onclick="popup('黑龙江省')">黑龙江省</a></td>
                                        <td><a href="#" onclick="popup('广西壮族自治区')">广西壮族自治区</a></td>
                                    </tr>
                                    <tr>
                                        <td><a href="#" onclick="popup('天津')">天津</a></td>
                                        <td><a href="#" onclick="popup('湖北省')">湖北省</a></td>
                                        <td><a href="#" onclick="popup('湖南省')">湖南省</a></td>
                                        <td><a href="#" onclick="popup('江西省')">江西省</a></td>
                                        <td><a href="#" onclick="popup('辽宁省')">辽宁省</a></td>
                                        <td><a href="#" onclick="popup('新疆维吾尔自治区')">新疆维吾尔自治区</a></td>
                                    </tr>
                                    <tr>
                                        <td><a href="#" onclick="popup('重庆')">重庆</a></td>

                                        <td><a href="#" onclick="popup('青海省')">青海省</a></td>
                                        <td><a href="#" onclick="popup('山东省')">山东省</a></td>
                                        <td><a href="#" onclick="popup('山西省')">山西省</a></td>
                                        <td><a href="#" onclick="popup('陕西省')">陕西省</a></td>
                                        <td><a href="#" onclick="popup('宁夏回族自治区')">宁夏回族自治区</a></td>
                                    </tr>
                                    <tr>

                                        <td><a href="#" onclick="popup('四川省')">四川省</a></td>
                                        <td><a href="#" onclick="popup('台湾省')">台湾省</a></td>
                                        <td><a href="#" onclick="popup('吉林省')">吉林省</a></td>
                                        <td><a href="#" onclick="popup('贵州省')">贵州省</a></td>
                                        <td><a href="#" onclick="popup('西藏自治区')">西藏自治区</a></td>
                                        <td><a href="#" onclick="popup('香港特别行政区')">香港特别行政区</a></td>
                                    </tr>
                                    <tr>
                                        <td><a href="#" onclick="popup('江苏省')">江苏省</a></td>
                                        <td><a href="#" onclick="popup('广东省')">广东省</a></td>
                                        <td><a href="#" onclick="popup('云南省')">云南省</a></td>
                                        <td><a href="#" onclick="popup('浙江省')">浙江省</a></td>
                                        <td><a href="#" onclick="popup('浙江省舟山')">浙江省舟山</a></td>

                                    </tr>
                                </table>
                            </th>
                        </tr>

                    </table>
                </form:form>
                <br/>


                <br/>
                <table id="tdata1" class="display" cellspacing="0" cellpadding="0" border="0" width="100%">
                    <thead>
                    <tr>
                        <th width="100px">省份</th>
                        <th width="70px">类型</th>
                        <th width="60px">起重</th>
                        <th width="100px">起价</th>
                        <th width="60px">续重加价</th>
                        <th width="60px">1kg价(韵达)</th>
                        <th width="60px">到达天数</th>
                        <th width="80px">实际价（指定重量）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="prvProvince"></c:set>
                    <c:forEach items="${expressCodes}" var="excd" varStatus="index">
                        <tr>

                            <c:if test="${prvProvince != null && prvProvince eq excd.province}">
                                <script type="text/javascript">
                                    $("#province" +${index.index-1}).attr("rowspan", 2);
                                </script>
                            </c:if>
                            <c:if test="${prvProvince == null || prvProvince ne excd.province}">
                                <td id="province${index.index}" class="a-center">${excd.province}</td>
                            </c:if>
                            <c:set var="prvProvince">${excd.province}</c:set>
                            <td class="a-center">${excd.type}</td>
                            <td class="a-right">${excd.init_weight}</td>
                            <td class="a-right">${excd.init_price}</td>
                            <td class="a-right">${excd.increase_price}</td>
                            <td class="a-right">
                                <c:if test="${excd.less1kg_price != 0 }">
                                    ${excd.less1kg_price}
                                </c:if>
                            </td>
                            <td class="a-right">${excd.days}</td>
                            <td class="a-right">
                                <c:if test="${excd.calcPrice != 0 }">
                                    ${excd.calcPrice}
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <br/>
        </div>

    </div>
    <div id="footer">
        <div id="credits">
            版权所有 ? 2012-2014, 易家生活坊, All Rights Reserved.
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
//        $("#tdata1 tr:odd").addClass("odd");
//        $("#tdata1 tr:even").addClass("even");
        var provinces = [
            {
                value: "ah anhui 安徽省",
                desc: "安徽省"
            },
            {
                value: "am aomen 澳门特别行政区",
                desc: "澳门特别行政区"
            },
            {
                value: "fj fujian 福建省",
                desc: "福建省"
            },
            {
                value: "gs gansu 甘肃省",
                desc: "甘肃省"
            },
            {
                value: "gd guangdong 广东省",
                desc: "广东省"
            },
            {
                value: "gx guangxi 广西壮族自治区",
                desc: "广西壮族自治区"
            },
            {
                value: "gz guizhou 贵州省",
                desc: "贵州省"
            },
            {
                value: "hn hainan 海南省",
                desc: "海南省"
            },
            {
                value: "hb hebei 河北省",
                desc: "河北省"
            },
            {
                value: "hn henan 河南省",
                desc: "河南省"
            },
            {
                value: "hlj heilongjiang 黑龙江省",
                desc: "黑龙江省"
            },
            {
                value: "hb hubei 湖北省",
                desc: "湖北省"
            },
            {
                value: "hn hunan 湖南省",
                desc: "湖南省"
            },
            {
                value: "jl jilin 吉林省",
                desc: "吉林省"
            },
            {
                value: "js jiangsu 江苏省",
                desc: "江苏省"
            },
            {
                value: "jx jiangxi 江西省",
                desc: "江西省"
            },
            {
                value: "ln liaoning 辽宁省",
                desc: "辽宁省"
            },
            {
                value: "nmg neimenggu 内蒙古自治区",
                desc: "内蒙古自治区"
            },
            {
                value: "nx ningxia 宁夏回族自治区",
                desc: "宁夏回族自治区"
            },
            {
                value: "qh qinghai 青海省",
                desc: "青海省"
            },
            {
                value: "xz xizang 西藏自治区",
                desc: "西藏自治区"
            },
            {
                value: "sd shandong 山东省",
                desc: "山东省"
            },
            {
                value: "sx shanxi 山西省",
                desc: "山西省"
            },
            {
                value: "sx shanxi 陕西省",
                desc: "陕西省"
            },
            {
                value: "sc sichuan 四川省",
                desc: "四川省"
            },
            {
                value: "tw taiwan 台湾省",
                desc: "台湾省"
            },
            {
                value: "tj tianjin 天津",
                desc: "天津"
            },
            {
                value: "xg xianggang 香港特别行政区",
                desc: "香港特别行政区"
            },
            {
                value: "xj xinjiang 新疆维吾尔自治区",
                desc: "新疆维吾尔自治区"
            },
            {
                value: "yn yunnan 云南省",
                desc: "云南省"
            },
            {
                value: "zj zhejiang 浙江省",
                desc: "浙江省"
            },
            {
                value: "cq chongqing 重庆",
                desc: "重庆"
            },
            {
                value: "zs zhoushan 浙江省舟山",
                desc: "浙江省舟山"
            },
            {
                value: "bj beijing 北京",
                desc: "北京"
            },
            {
                value: "sh shanghai 上海",
                desc: "上海"
            }
        ];

        $( "#province" ).autocomplete({
            minLength: 1,
            source: provinces,
            focus: function( event, ui ) {
                $( "#province" ).val( ui.item.desc );
                return false;
            },
            select: function( event, ui ) {
                $( "#province" ).val( ui.item.desc );
                $("#form").submit();
                return false;
            }
        })
        .data( "autocomplete" )._renderItem = function( ul, item ) {
            return $( "<li>" )
                .data( "item.autocomplete", item )
                .append( "<a>" + item.desc + "</a>" )
                .appendTo( ul );
        };
    });

    function popup(province){
       $("#province").val(province);
       $("#form").submit();
    }
</script>