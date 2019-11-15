<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/28 0028
  Time: 下午 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link href="static/easy/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/menu/metisMenu.css" rel="stylesheet"/>
    <link href="static/menu/horizontal.css" rel="stylesheet"/>
    <script src="static/easy/js/jquery.js"></script>
    <script src="static/easy/js/vue.js"></script>
    <script src="static/menu/metisMenu.js"></script>
    <script src="static/easy/js/popper.min.js"></script>
    <script src="static/easy/js/bootstrap.js"></script>
    <script src="static/page/jqpaginator.js"></script>

</head>
<body>
<div class="container-fluid" id="app">
    <div class="row p-1 shadow">
        <div class="col">
            <a class="text text-info" href="home/main">商城首页</a>
        </div>
        <div class="col">
            <h3 class="text-danger text-center">蜗牛商城</h3>
        </div>
        <div class="col w-100">
            <div class="row text-right w-100">
                <h5 class="text-right mt-1 ml-5">欢迎${sessionScope.user.account}</h5>
                <c:if test="${sessionScope.user==null}">
                    <div class="dropdown">
                        <button type="button" class="btn btn-primary dropdown-toggle ml-5" data-toggle="dropdown">
                            请选择登录方式
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="log/in">登录</a>
                            <a class="dropdown-item" href="register/in">注册</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                    <a href="log/out" class="mt-1 ml-2">注销</a>
                </c:if>
                <a href="buy/car" class="mt-1 text-info ml-2">购物车</a>
                <a href="order/show" class="mt-1 text-info ml-2">订单</a>
            </div>
        </div>
    </div>
    <!--商品分类菜单-->
    <div class="row mt-1">
        <div class="col">
            <div class="topbar-nav">
                <ul class="metismenu">
                    <c:forEach items="${list}" var="one">
                        <c:if test="${one.pid==0}">
                            <li class="text-white text-center" style="font-size: 20px;font-weight: bold"><a
                                    href="javascript:" >${one.name}</a>
                                <ul>
                                    <c:forEach items="${list}" var="two">
                                        <c:if test="${two.pid==one.id}">
                                            <li class="text-white text-center"
                                                style="font-size: 18px;font-weight: bold" ><a onclick="show(${two.id})"
                                                    href="javascript:">${two.name}</a>
                                                <ul>
                                                    <c:forEach items="${list}" var="three">
                                                        <c:if test="${three.pid==two.id}">
                                                            <li class="text-white text-center"
                                                                style="font-size: 16px;font-weight: bold"><a
                                                                    href="javascript:" onclick="show(${three.id})">${three.name}</a>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!--商品浏览区-->
    <div class="row mt-1">
        <div class="col">
            <div v-for="p in productdata">
                <div class="card float-left mt-3 " style="width: 33%; height: 300px;">
                    <div class="card-header"><a :href="'product/one?id='+p.id" style="color: black">{{p.name}}</a></div>
                    <div class="card-body">
                        <div style="height: 200px">
                            <a :href="'product/one?id='+p.id">
                                <img :src="p.img" style=" height: 160px">
                            </a>
                        </div>
                        <div class="text-danger text-right"><a :href="'product/one?id='+p.id">￥:{{p.price}}</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="w-100">
        <div id="page" class="pagination pagination4" style="float: right "></div>
    </div>
</div>
<script>
    var app = new Vue({
        el: "#app",
        data: {
            productdata: []
        }
    });

    $(".metismenu").metisMenu();

    function showProductData() {
        $.post("product/limit", {
            pageNum: pageNum,
            pageSize: 6
        }, function (data) {
            var res = $.parseJSON(data);
            app.productdata = res.list;
            $('#page').jqPaginator('option', {
                totalPages: res.pagecount
            });
        })
    }

    //showProductData();
    function setpage() {
        $("#page").jqPaginator({
            totalPages: 100,
            currentPage: 1,
            onPageChange: function (num, type) {
                pageNum = num;
                showProductData();
            }
        });
    }

    setpage();

    function show(id) {
        $.post("product/type",{
            pageNum: pageNum,
            pageSize: 6,
            tid:id
        },function (data) {
            var res = $.parseJSON(data);
            app.productdata = res.list;
            $('#page').jqPaginator('option', {
                totalPages: res.pagecount
            });
        })
    }

</script>
<script color="0,0,255" opacity='0.6' count="50" src="https://cdn.bootcss.com/canvas-nest.js/2.0.4/canvas-nest.js"></script>
</body>
</html>
