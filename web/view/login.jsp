<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/28 0028
  Time: 下午 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="static/menu/metisMenu.css" rel="stylesheet" />
    <link href="static/menu/horizontal.css" rel="stylesheet" />
    <script src="static/easy/js/jquery.js"></script>
    <script src="static/easy/js/vue.js"></script>
    <script src="static/menu/metisMenu.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row mt-5 w-100">
            <div class="col-sm-4 offset-sm-4 ">
                <div class="card ">
                    <div class="card-header bg-dark text-white text-center ">用户登录</div>
                    <form class="card-body" action="log/in" method="post">
                        <div>登录名:</div>
                        <input type="text" name="account" placeholder="请输入用户名" class="form-control form-control-sm">
                        <div>密码:</div>
                        <input type="password" name="password" placeholder="请输入密码" class="form-control form-control-sm">
                        <button class="btn btn-info w-100 mt-2" type="submit">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>