<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2020/11/5
  Time: 23:06
  To change this template use File | Settings | File Templates.
  留言：
   <script type="text/javascript">alert("html 注入")</script>
  测试
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>displayMessage</title>
</head>
<body>
    <form action="res" method="post">
        用户名：<input type="text" name="username"><br>
        留言：<textarea rows="6" cols="50" name="message">
            </textarea><br>
        反转文本框：<input type="text" name="text">
        <input type="submit"value="提交">
    </form>
</body>
</html>
