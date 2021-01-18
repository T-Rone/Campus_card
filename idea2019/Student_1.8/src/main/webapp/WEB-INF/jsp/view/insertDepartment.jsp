<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>增加专业</title>
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

    <form action="${ctx}/view/insertDepartment.do" method="post">
        <table>
            <!-- 就加了这个？？ 欧克 不null point??fuck -->
            <input type="hidden" name="flag" value="2">
            <tr>
                <td>专业名称：</td>
                <td><input type="text" id="depart_name" name="depart_name"></td>
            </tr>
        </table>
        <input type="submit" value="增加">
        <input type="reset" value="重置">
    </form>
</center>
</body>
</html>
