<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2020/11/5
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="itcast" uri="http://mycompany.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
用户名：${name}<br>
留言：${itcast:filter(message)}<br>
反转文本框：${itcast:reverse(text)}
</body>
</html>
