<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2021/1/12
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload.do" method="post" enctype="multipart/form-data">
    file: <input type="file" name="multipartFile"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
