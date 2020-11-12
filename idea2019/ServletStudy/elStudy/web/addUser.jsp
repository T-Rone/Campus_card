<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2020/11/7
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户界面</title>
</head>
<body>
<form action="addUser"  id="userForm" method="post">
    <table align="center" border="0">
        <tr>
            <td >用户名：</td><td><input type="text" name="username" id="username" size="20"/></td>
        </tr>
        <tr>
            <td >密码：</td><td><input type="text" name="password" id="password" size="20"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="添加">&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="reset" value="取消">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
