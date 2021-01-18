<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://www.tdragon.com" %>
<html lang="en" class="no-js">
<head>
    <title>用户登录界面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="jsUi/Css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="jsUi/Css/demo.css"/>
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="jsUi/Css/component.css"/>
    <!--[if IE]>
    <script src="jsUi/Js/html5.js"></script>
    <![endif]-->
    <script src="jsUi/Js/html5.js"></script>

    <script>
        function check() {
            if (document.getElementById("name").value == "") {
                alert("用户名不能为空！");
                return false;
            } else if (document.getElementById("password").value == "") {
                alert("密码不能为空！");
                return false;
            } else {
                alert("提交成功！");
                return true;
            }
        }

        function refresh() {
            var img = document.getElementById("kaptcha");
            img.src = "${pageContext.request.contextPath}/kaptcha?" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎登录</h3>
                <form action="login.do" name="loginForm" method="post">
                    <c:choose>
                        <c:when test="${requestScope.message == null }">
                            <span style="padding-top: 5px">允许以中文名称登录</span>
                        </c:when>
                        <c:otherwise>
                            <span style="padding-top: 5px"><font color="red">${requestScope.message}</font></span>
                        </c:otherwise>
                    </c:choose>

                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input id="name" name="name" class="text" style="color: #FFFFFF !important" type="text"
                               placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input id="password" name="password" class="text"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                    </div>

                    <img id="kaptcha" src="${pageContext.request.contextPath}/kaptcha" alt="验证码" onclick="refresh()"
                         width="45%">
                    <span>
								<input type="text" name="accesscode" placeholder="请输入验证码！"
                                       style="height: 37.13px;line-height: 37.13px;width: 110px; margin-left: -3px;border: white;"/>
							</span>
                    <div class="mb2">

                        <input class="act-but submit" type="submit" onclick="check()" style="color: #FFFFFF" value="登录">
                        <a href="view/insertUser.do?flag=1" class="act-but submit"
                           style="color: #FFFFFF;width:233.2px;margin-top:-53.2px;margin-left:86px;height:53.2px">
                            没有账号?立即注册
                        </a>

                    </div>

                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="jsUi/Js/TweenLite.min.js"></script>
<script src="jsUi/Js/EasePack.min.js"></script>
<script src="jsUi/Js/rAF.js"></script>
<script src="jsUi/Js/demo-1.js"></script>
<div style="text-align:center;">
</div>
</body>
</html>