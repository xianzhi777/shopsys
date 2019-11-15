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
</head>
<body>
<div class="container-fluid">
    <div class="row p-1 shadow">
        <div class="col">
            <a class="text text-info" href="home/main">商城首页</a>
        </div>
        <div class="col">
            <h3 class="text-danger text-center">选择地址</h3>
        </div>
        <div class="col ">

        </div>
    </div>
    <div class="row mt-5 w-100" id="address">
        <div class="col-sm-4 offset-sm-4 ">
            <div class="card ">
                <div class="card-header bg-dark text-white text-center ">收货地址</div>
                <form class="card-body" action="address/select" method="post">
                    <div v-for="(p,index) in addressdata">
                        <div class="row" style="font-size: 16px ;text-indent: 1em">
                            <input type="radio" :value="p.id" name="address" checked>
                            <div>姓名:{{p.name}}</div>&nbsp;&nbsp;
                            <div>省市:{{p.province}}</div>
                            <div>{{p.city}}</div>
                        </div>
                        <div class="row" style="font-size: 12px;text-indent: 2em">
                            <div>具体地址:{{p.detailAddress}}</div>&nbsp;&nbsp;
                        </div>
                        <div class="row" style="font-size: 12px;text-indent: 2em">
                            <div>电话{{p.phone}}</div>&nbsp;&nbsp;
                        </div>
                        <hr>
                    </div>
                    <div>
                        <div class="row"  style="font-size: 16px ;text-indent: 1em">
                            <input type="radio" value="-1" name="address">
                            <div >新增地址</div>
                        </div>
                    </div>
                    <button class="btn btn-info  mt-2" type="submit">确定</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var app = new Vue({
        el: "#address",
        data: {
            addressdata: []
        }
    });
    
    function showaddress() {
        $.post("address/all",{},function (data) {
            var addressList=$.parseJSON(data);
            app.addressdata=addressList;
        })
    }
    showaddress();
</script>
</html>