<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2021/1/1
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载文件页面</title>
</head>
<body>
<img src="upload/${sessionScope.filename}" alt="${filename}" name="image" width="6%">
<a href="${pageContext.request.contextPath}/download.do?name=${filename}">下载</a>
</body>
</html>
