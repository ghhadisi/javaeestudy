<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>

<!--
    描述：菜单栏
-->
<%@include file="/jsp/header.jsp" %>

<div class="container">
    <div class="row">

        <div style="margin:0 auto; margin-top:10px;width:950px;">
            <strong>我的订单</strong>
            <c:if test="${empty page}">

                <div class="row" style="width:1210px;margin:0 auto;">
                    <div class="col-md-12">
                        <h1>暂无订单</h1>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty page}">

                <table class="table table-bordered">

                    <c:forEach items="${page.records}" var="item">
                        <tbody>
                        <tr class="success">
                            <th colspan="5">
                                <a href="${pageContext.request.contextPath}/orderServlet?method=findOrderByOid&oid=${item.oid}">订单编号:${item.oid}</a>
                                总金额:¥${item.total}元
                                <c:if test="${item.state==1}">
                                    <a href="${pageContext.request.contextPath}/orderServlet?method=findOrderByOid&oid=${item.oid}">付款</a>
                                </c:if>
                                <c:if test="${item.state==2}">未发货</c:if>
                                <c:if test="${item.state==3}">
                                    <a href="#">签收</a>
                                </c:if>
                                <c:if test="${item.state==4}">结束</c:if>
                            </th>
                        </tr>
                        <tr class="warning">
                            <th>图片</th>
                            <th>商品</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>小计</th>
                        </tr>

                        <c:forEach items="${item.list}" var="p">
                            <tr class="active">
                                <td width="60" width="40%">
                                    <input type="hidden" name="id" value="22">
                                    <img src="${pageContext.request.contextPath}/${p.product.pimage}" width="70" height="60">
                                </td>
                                <td width="30%">
                                    <a target="_blank"> ${p.product.pname}</a>
                                </td>
                                <td width="20%">
                                        ${p.product.shop_price}
                                </td>
                                <td width="10%">
                                        ${p.quantity}
                                </td>
                                <td width="15%">
                                    <span class="subtotal">￥${p.total}</span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:forEach>
                </table>
            </c:if>


            <%--<table class="table table-bordered">

                <tbody>
                <tr class="success">
                    <th colspan="5">订单编号:9002</th>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <tr class="active">
                    <td width="60" width="40%">
                        <input type="hidden" name="id" value="22">
                        <img src="${pageContext.request.contextPath}/products/2/dadonggua.jpg" width="70" height="60">
                    </td>
                    <td width="30%">
                        <a target="_blank"> 有机蔬菜 大冬瓜...</a>
                    </td>
                    <td width="20%">
                        ￥298.00
                    </td>
                    <td width="10%">
                        5
                    </td>
                    <td width="15%">
                        <span class="subtotal">￥596.00</span>
                    </td>
                </tr>
                </tbody>
            </table>--%>
        </div>
    </div>

    <%@include file="/jsp/pageFile.jsp" %>
</div>

<%@include file="footer.jsp"%>

</body>

</html>