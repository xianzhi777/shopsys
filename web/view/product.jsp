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
            <h3 class="text-danger text-center">蜗牛商城</h3>
        </div>
        <div class="col w-100">
            <div class="row text-right w-100">
                <h5 class="text-right mt-1 ml-5">欢迎${sessionScope.user.account}</h5>
                <a href="log/out" class="mt-1 ml-2">注销</a>
                <a href="buy/car" class="mt-1 text-info ml-2">购物车</a>
            </div>
        </div>
    </div>
    <div class="row ">
        <div class="col col-sm-2 mt-3"></div>
        <div class="col col-sm-8 mt-3">
            <div class="row w-100 h-100" style="border: 1px solid black ">
                <div class="col col-sm-4 text-center " style="height: 500px;line-height: 500px">
                    <img class="mt-5" style="width: 300px ;" src=${product.img}>
                </div>
                <div class="col col-sm-8" style="height: 500px">
                    <h2 class="mt-3 text-center"> ${product.name}</h2>
                    <h3 class="text text-danger text-center">${product.price}</h3>
                    <div style="height: 200px;text-indent: 2em">商品详情:${product.detail}</div>
                    <div style="text-indent: 2em">商品剩余:${product.inventory}</div>
                    <div class="text-center">
                        <button class="btn btn-warning" onclick="reduce()">-</button>
                        <input id="num" class="text" value="1" class="form-control-sm">
                        <button class="btn btn-warning" onclick="add()">+</button>
                    </div>
                    <div class="text-center">
                        <button class="btn btn-info btn-large" onclick="buy()">购买</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    function add() {
        var num = $("#num").val();
        num++;
        if (num <= ${product.inventory}) {
            $("#num").val(num);
        } else {
            num--;
        }
    }

    function reduce() {
        var num = $("#num").val();
        num--;
        if (num >= 1) {
            $("#num").val(num);
        } else {
            num++;
        }
    }

    function checkNum() {
        var num = $("#num").val();
        var inventory =${product.inventory};
        if (inventory <= 0) {
            $("#num").val(0);
            $("#num").attr("disabled", "disabled");
        }
    }

    function buy() {
        var num = $("#num").val();
        var id =${id};
        if(num>0){
            $.post("buy/something", {
                "productid": id,
                "num": num
            }, function (data) {
                if (data) {
                    alert("成功");
                } else {
                    alert("失败");
                }
                checkNum();
            });
        }else {
            alert("购买失败")
        }

    }
    checkNum();



</script>
</body>
</html>
