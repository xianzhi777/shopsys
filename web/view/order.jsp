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
    <div class="container-fluid" >
        <div class="row p-1 shadow">
            <div class="col">
                <a class="text text-info" href="home/main">商城首页</a>
            </div>
            <div class="col">
                <h3 class="text-danger text-center">蜗牛商城订单界面</h3>
            </div>
            <div class="col w-100">
                <div class="row text-right w-100">
                    <h5 class="text-right mt-1 ml-5">欢迎${sessionScope.user.account}</h5>
                    <a href="log/out" class="mt-1 ml-2">注销</a>
                    <a href="buy/car" class="mt-1 text-info ml-2">购物车</a>
                </div>
            </div>
        </div>
        <div class="row" >
            <div class="col ml-2" id="withoutpay">
                <span style="font-size: 16px;font-weight: bold" >未支付订单</span>
                <div v-for="a in iddata" >
                    <div >
                        <div class="card">
                            <div class="card-header">
                                <div >
                                    订单编号:{{a.id}}
                                </div>
                            </div>
                            <div class="card-body" v-for="(p,index) in state2data" v-if="a.id==p.orderId">
                                <div class="row">
                                    <div class="col" > 商品名称:{{p.name}}</div>
                                    <div class="col" :id="'price'+index">单价:{{p.price}}</div>
                                    <div class="col" :id="'buyNum'+index">购买数量:{{p.buyNum}}</div>
                                    <div class="col" id="total">小计:{{p.price*p.buyNum}}</div>
                                </div>
                                <hr>
                            </div>
                            <div class="card-footer text-right">
                                <div style="font-size:25px;color: red;font-weight: bold">总计:{{a.total}}</div>
                                <button class="btn btn-dark" v-on:click="del(a.id)">删除订单</button>
                                <button class="btn btn-success" v-on:click="pay(a.id)">确认支付</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col ml-2" id="haspaid">
                <span style="font-size: 16px;font-weight: bold" >已支付订单</span>
                <div v-for="a in iddata" >
                    <div >
                        <div class="card">
                            <div class="card-header">
                                <div >
                                    订单编号:{{a.id}}
                                </div>
                            </div>
                            <div class="card-body" v-for="(p,index) in state2data" v-if="a.id==p.orderId">
                                <div class="row">
                                    <div class="col" > 商品名称:{{p.name}}</div>
                                    <div class="col" :id="'price'+index">单价:{{p.price}}</div>
                                    <div class="col" :id="'buyNum'+index">购买数量:{{p.buyNum}}</div>
                                    <div class="col">小计:{{p.price*p.buyNum}}</div>
                                </div>
                                <hr>
                            </div>
                            <div class="card-footer text-right">
                                <div style="font-size:25px;color: red;font-weight: bold">总计:{{a.total}}</div>
                                <button class="btn btn-dark" v-on:click="del(a.id)">删除订单</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
    var app = new Vue({
        el: "#withoutpay",
        data: {
            state2data: [],
            iddata:[]
        }
    });

    function showstat2() {
        $.post("order/withoutpay",{},function (data) {
            var myorder=$.parseJSON(data);
            app.state2data=myorder.oneOrderList;
            app.iddata=myorder.orderList;
        })
    }
    showstat2();

    var abc=new Vue({
        el:"#haspaid",
        data:{
            stat3data:[],
            iddata:[]
        }
    });

    function showstat3() {
        $.post("order/haspaid",{},function (data) {
            var myorder=$.parseJSON(data);
            abc.state2data=myorder.oneOrderList;
            abc.iddata=myorder.orderList;
        })
    }
    showstat3();
    
    function pay(id) {
        $.post("pay/now",{
            aid:id
        },function (data) {
            if(data==1){
                location.href="order/show";
            }else {
                alert("支付失败!")
            }
        })
    }

    function del(id) {
        $.post("order/del",{
            id:id
        },function (data) {
            location.href="order/show";
        })
    }
</script>
</body>
</html>