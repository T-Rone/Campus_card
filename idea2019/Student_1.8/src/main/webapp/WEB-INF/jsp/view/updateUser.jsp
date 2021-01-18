<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>修改用户信息</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/style.css"/>
    <script type="text/javascript" src="../jsUi/Js/jquery.js"></script>
    <script type="text/javascript" src="../jsUi/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../jsUi/Js/bootstrap.js"></script>
    <script type="text/javascript" src="../jsUi/Js/ckform.js"></script>
    <script type="text/javascript" src="../jsUi/Js/common.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<body>
<form action="${ctx}/view/updateUser.do" id="userForm" method="post" enctype="multipart/form-data">
    <!-- 隐藏表单，flag表示添加标记 -->
    <input type="hidden" name="flag" value="2">
    <input type="hidden" name="id" value="${user.id}">
    <table width="100%" border="0" cellpadding="0" cellspacing="10"
           class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="font3 fftd">
                <table>

                    <tr>
                        <td>用户名：<input type="text" id="name" name="name" value="${user.name}"></td>
                    </tr>
                    <tr>
                        <td>密码：<input type="text" id="password" name="password" value="${user.password}"></td>
                    </tr>
                    <tr>
                        <td> 照片：
                            <input type="file" name="multipartFile"><br>
                            <%--						<img src="../${user.image}" alt="${user.image}" name="image" width="10%">--%>
                        </td>
                    </tr>


                </table>
            </td>
        </tr>
        <tr>
            <td align="left" class="fftd"><input type="submit" value="修改">&nbsp;&nbsp;
                <input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>