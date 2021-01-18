<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--牛逼text/html; 两者有空格报错charset=UTF-8 --%>
<html>
<head>
    <title>修改成绩信息</title>
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
<form action="${ctx}/view/updateGrade.do" id="gradeForm" method="post">
    <!-- 隐藏表单，flag表示添加标记 -->
    <input type="hidden" name="flag" value="2">
    <input type="hidden" name="id" value="${grade.id}">
    <table width="100%" border="0" cellpadding="0" cellspacing="10"
           class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="font3 fftd">
                <table>
                    <tr>
                        <td class="font3 fftd">学号：
                            <select name="usid" style="width:100px;">
                                <option value="0">--学号选择--</option>
                                <c:forEach items="${requestScope.students }" var="student">
                                    <c:choose>
                                        <c:when test="${grade.student.usid == student.usid }">
                                            <option value="${student.usid }"
                                                    selected="selected">${student.usid }</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${student.usid }">${student.usid }</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>课程号：
                            <select name="cid" style="width:100px;">
                                <option value="0">--课程号选择--</option>
                                <c:forEach items="${requestScope.courses }" var="course">
                                    <c:choose>
                                        <c:when test="${grade.course.cid == course.cid }">
                                            <option value="${course.cid }" selected="selected">${course.cid }</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${course.cid }">${course.cid }</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>


                        </td>
                    </tr>
                    <tr>
                        <td>成绩：<input type="text" id="score" name="score" value="${grade.score}"></td>
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