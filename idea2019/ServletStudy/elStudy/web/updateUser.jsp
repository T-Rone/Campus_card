<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2020/11/7
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户界面</title>
</head>
<body>
<form action="updateUser" id="userForm" method="post">
    <!-- 隐藏表单，flag表示添加标记 -->
    <input type="hidden" name="flag" value="2">
    <input type="hidden" name="id" value="${sessionScope.user.id}">
    <table border="1" cellpadding="15" cellspacing="0" bgcolor="#7fffd4">
        <tr>
            <td >用户名：</td><td><input type="text" name="username" id="username" size="20" value="${sessionScope.user.username}"/></td>
        </tr>
        <tr>
            <td >密码：</td><td><input type="text" name="password" id="password" size="20" value="${sessionScope.user.password}"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="修改">&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" value="取消">
            </td>
        </tr>
    </table>
</body>
</html>
