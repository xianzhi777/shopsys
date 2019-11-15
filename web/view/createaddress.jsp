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
            <div class="col"></div>
        </div>
        <form class="row" action="address/create" method="post">
            <div class="col col-sm-2"></div>
            <div class="col col-sm-8 ">
                <div class="row w-100 mt-2 text-center" >
                        <div class="col w-100">
                            <span>姓名:</span>
                            <input type="text" name="n" style="width: 300px">
                        </div>
                </div>
                <div class="row w-100 mt-2 text-center" >
                    <div class="col w-100">
                        <span>电话:</span>
                        <input type="text" name="phone" style="width: 300px">
                    </div>
                </div>
                <div class="row w-100 mt-2 text-center" >
                    <div class="col w-100">
                        <select id="selProvince" onchange="changeCity( )" style="width:150px" name="province">
                            <option>省</option>
                        </select>
                        <select id="selCity" style="width:150px" name="city">
                            <option>市</option>
                        </select>
                    </div>
                </div>
                <div class="row w-100 mt-2 text-center" >
                    <div class="col w-100">
                        <span>具体地址:</span>
                        <input type="text" name="detail" style="width: 300px">
                    </div>
                </div>
                <div class="row w-100 mt-2 text-center">
                    <div class="col w-100">
                        <button class="btn btn-danger" style="width: 400px" onclick="submit">确定</button>
                    </div>

                </div>
            </div>
            <div class="col col-sm-2"></div>
        </form>
    </div>
<script>
    function addProvince() {
        var province = document.getElementById("selProvince");
        province.add(new Option("北京市", "北京市", null));
        province.add(new Option("上海市", "上海市", null));
        province.add(new Option("河南省", "河南省", null));
    }

    function changeCity() {
        var province = document.getElementById("selProvince").value;
        var city = document.getElementById("selCity");
        city.innerHTML = "";//先清空再赋值
        switch(province) {
            case "北京市":
                city.add(new Option("市"));
                city.add(new Option("朝阳区", "朝阳区"), null);
                city.add(new Option("东城区", "东城区"), null);
                city.add(new Option("海淀区", "海淀区"), null);
                break;
            case "上海市":
                city.add(new Option("市"));
                city.add(new Option("宝山区", "宝山区"), null);
                city.add(new Option("长宁区", "长宁区"), null);
                city.add(new Option("丰贤区", "丰贤区"), null);
                break;
            case "河南省":
                city.add(new Option("市"));
                city.add(new Option("郑州市", "郑州市"), null);
                city.add(new Option("洛阳市", "洛阳市"), null);
                city.add(new Option("安阳市", "安阳市"), null);
                break;
            default:
                city.add(new Option("市"));
        }
    }
    window.onload(addProvince());
</script>
</body>
</html>
