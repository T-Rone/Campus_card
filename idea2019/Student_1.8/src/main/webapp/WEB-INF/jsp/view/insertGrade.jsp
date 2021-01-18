<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 头文件写错 出现 class not found 错误 我把那个查询页面的复制过来出错的 哈哈 -->
<html>
<head>
    <title>插入成绩</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../jsUi/Css/style.css"/>
    <script type="text/javascript" src="../jsUi/Js/jquery.js"></script>
    <%--    <script type="text/javascript" src="../jsUi/Js/jquery.sorted.js"></script>--%>
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
<center>
    <form action="${ctx}/view/insertGrade.do" method="post">
        <table>
            <input type="hidden" name="flag" value="2">
            <tr>
                <td>学号：
                    <select name="usid">
                        <option value="0">--学号选择--</option>
                        <c:forEach items="${requestScope.students }" var="student">
                            <option value="${student.usid }">${student.usid }</option>
                        </c:forEach>
                    </select>


                </td>
            </tr>
            <tr>
                <td>课程号：
                    <select name="cid">
                        <option value="0">--课程号选择--</option>
                        <c:forEach items="${requestScope.courses }" var="course">
                            <option value="${course.cid }">${course.cid }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>


            <tr>
                <td>分数：<input type="text" id="score" name="score"></td>
            </tr>
        </table>
        <input type="submit" value="增加">
        <input type="reset" value="重置">
    </form>
</center>
</body>
</html>
