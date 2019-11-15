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
            <h3 class="text-danger text-center">蜗牛商城购物车</h3>
        </div>
        <div class="col w-100">
            <div class="row text-right w-100">
                <h5 class="text-right mt-1 ml-5">欢迎${sessionScope.user.account}</h5>
                <a href="log/out" class="mt-1 ml-2">注销</a>
                <a href="order/show" class="mt-1 text-info ml-2">订单</a>
            </div>
        </div>
    </div>
    <div id="shopcar">
        <div v-for="(p,index) in oneorderdata">
            <div class="row text-center" style="height: 120px; line-height: 120px">
                <div class="col col-sm-1"></div>
                <div class="col col-sm-1"><img style="height: 100px" :src=p.img></div>
                <div class="col col-sm-3 text-right">{{p.name}}</div>
                <div class="col col-sm-3 ">
                    <button class="btn btn-warning" :id="'reduce'+index" v-on:click="reduce(index)">-</button>
                    <span :id="'buyNum'+index">{{p.buyNum}}</span>
                    <button class="btn btn-warning" id="'add'+index" v-on:click="add(index)">+</button>
                </div>
                <div class="col col-sm-1">{{p.price}}</div>
                <div class="col col-sm-2" :id="'product'+index"></div>
                <div class="col col-sm-1"><button class="btn btn-success" v-on:click="del(p.id)">删除</button></div>
            </div>
            <hr>
        </div>
        <div class="row" style="height: 100px" >
            <div class="col col-3"></div>
            <div class="col col-6"><div id="total" class="text-right" style="font-size: 40px;color: red;font-weight: bold;line-height: 100px"></div></div>
            <div class="col col-3">
                <div v-if="oneorderdata.length>0">
                    <button class="btn w-100 h-75 bg-info" onclick="confirmbuy()">确认支付</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var app = new Vue({
        el: "#shopcar",
        data: {
            oneorderdata: []
        },
        methods:{
             onclick:function(index) {

             }
        }
    });

    function showOneorderdata() {
        $.post("buy/show", {}, function (data) {
            var res = $.parseJSON(data);
            app.oneorderdata = res;
            gettotal();
        })
    }

    function gettotal() {
        var total=0;
        $.each(app.oneorderdata,function (i,p) {
            var id="#buyNum"+i;
            var productid="#product"+i;
            var num=$(id).html();
            $(productid).html(p.price*num);
            total+=num*p.price;
        });
        $("#total").html("总计:"+total+"元");
    }

    function del(id) {
        change();
       $.post("buy/del",{
           "id":id
       },function (data) {
          showOneorderdata();
       })
    }

    function add(index) {
        var id="#buyNum"+index;
        var num=$(id).html();
        num++;
        $(id).html(num);
        showOneorderdata();
    }

    function reduce(index) {
        var id="#buyNum"+index;
        var num=$(id).html();
        if(num>=2){
            num--;
        }
        $(id).html(num);
        showOneorderdata();
    }
    showOneorderdata();

    window.onload=function () {
        showOneorderdata();
    };

    var arr=[];
    var str="";
    function confirmbuy() {
        change();
       location.href="address/show";
    }


    function change(){
        $.each(app.oneorderdata,function (i,p){
            var address="#buyNum"+i;
            var pid=p.id;
            var num=$(address).html();
            str+=pid+":"+num+",";
        });
        $.post("buy/leave",{
            arr:str
        },function () {

        })
    }
    window.onbeforeunload=function () {
        change();
    }
</script>
</body>
</html>
