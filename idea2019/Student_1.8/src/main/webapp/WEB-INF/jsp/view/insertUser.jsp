<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <title>用户注册界面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/demo.css"/>
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/component.css"/>
    <!--[if IE]>
    <script src="jsUi/Js/html5.js"></script>
    <![endif]-->
    <script src="jsUi/Js/html5.js"></script>

    <script>
        function check() {
            if (document.getElementById("name").value === "") {
                alert("用户名不能为空！");
                window.history.back(-1);
            } else if (document.getElementById("password").value === "") {
                alert("密码不能为空！");
                window.history.back(-1);
            } else if (document.getElementById("password").value !== document.getElementById("confirm").value) {
                alert("两次密码不一致！请重新输入！");
                window.history.back(-1);
            } else {
                alert("提交成功！");
            }
        }
    </script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎注册</h3>
                <form action="${ctx}/view/insertUser.do?flag=2" name="loginForm" method="post"
                      enctype="multipart/form-data">

                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input id="name" name="name" class="text" style="color: #FFFFFF !important" type="text"
                               placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input id="password" name="password" class="text"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" type="password"
                               placeholder="请输入密码">
                    </div>

                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input id="confirm" name="confirm" class="text"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" type="password"
                               placeholder="请再次输入密码">
                    </div>

                    <input type="file" name="multipartFile">
                    <input type="hidden" value="${sessionScope.captcha}" name="accesscode"/>
                    <div class="mb2">
                        <input class="act-but submit" type="submit" onclick="check()"
                               style="color: #FFFFFF;margin: 0 auto;" value="注册">
                    </div>

                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="../jsUi/Js/TweenLite.min.js"></script>
<script src="../jsUi/Js/EasePack.min.js"></script>
<script src="../jsUi/Js/rAF.js"></script>
<script src="../jsUi/Js/demo-1.js"></script>
<div style="text-align:center;">
</div>
</body>
</html>