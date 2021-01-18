<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>修改课程信息</title>
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
<form action="${ctx}/view/updateCourse.do" id="courseForm" method="post">
    <!-- 隐藏表单，flag表示添加标记 -->
    <input type="hidden" name="flag" value="2">
    <input type="hidden" name="id" value="${course.id}">
    <table width="100%" border="0" cellpadding="0" cellspacing="10"
           class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="font3 fftd">
                <table>
                    <tr>
                        <td class="font3 fftd">课程号：<input type="text" name="cid" id="cid" size="20"
                                                          value="${course.cid}"/></td>
                    </tr>

                    <tr>
                        <td>课程名称：<input type="text" id="cname" name="cname" value="${course.cname}"></td>
                    </tr>
                    <tr>
                        <td>开课学期：<input type="text" id="term" name="term" value="${course.term}"></td>
                    </tr>
                    <tr>
                        <td>学时：<input type="text" id="period" name="period" value="${course.period}"></td>
                    </tr>
                    <tr>
                        <td>绩点：<input type="text" id="credit" name="credit" value="${course.credit}"></td>
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