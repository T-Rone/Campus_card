<%--
  Created by IntelliJ IDEA.
  User: tdragon
  Date: 2020/11/5
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <%  pageContext.setAttribute("username","itcast");
          request.setAttribute("bookname","Jave Web");
          session.setAttribute("username","T-DRAGON");
          application.setAttribute("bookname","java并发");
            response.addCookie(new Cookie("name","T-Rix"));
      %>
        表达式\${pageScope.username}的值：${pageScope.username} <br>
        表达式\${requestScope.bookname}的值：${requestScope.bookname} <br>
        表达式\${sessionScope.username}的值：${sessionScope.username} <br>
        表达式\${applicationScope.bookname}的值：${applicationScope.bookname}
  <br>
      //在四个作用域中按顺序查找： \${username}的值${username}
  <form action="">
      num1:<input type="text" name="num1"><br>
      num2:<input type="text" name="num"><br>
      num3:<input type="text" name="num"><br>
      <input type="submit" value="提交">&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
      <br> num1:${param.num1}<br>
      num2:${empty paramValues}<br>
      num3:${paramValues.num[1]}<br>
      ${header.host}
      ${headerValues["Accept-Language"][0]}
      ${headerValues["Accept-Language"][1]}
      ${headerValues["Content-Type"][0]}
      ${headerValues["Accept-Encoding"][0]}
      ${headerValues["User-Agent"][0]}<br>
      Cookie对象的信息：${cookie.name}<br>
      对象名称和值：${cookie.name.name}&nbsp;&nbsp;&nbsp;&nbsp;${cookie.name.value}
      //读取web.xml初始化参数:${initParam.website}
      <a href="${initParam.website}">我的网站</a>
  </form>
      <%--    escapexml default true 特殊字符转义--%>
      <c:out value="我太难了！" default="默认值" escapeXml="false" />
      application域中的skillname:
      <c:set var="skillname" value="elk搜索引擎技术" scope="application"/>
      <c:out value="${skillname}"/><hr>
<%--      <jsp:useBean id="user" class="com.bean.User"/>--%>
<%--            <c:set value="" target="${user}" property="usename"></c:set>--%>
        <c:if test="${pageScope.username}=='itcast'">
            c if test 测试成功
        </c:if>
  <c:choose>
      <c:when test="${empty param.num1}">
          num1 不为空；
      </c:when>
      <c:otherwise>
          num1第一个文本框不为空
      </c:otherwise>
  </c:choose>
  </body>
</html>
