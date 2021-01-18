<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>专业信息</title>
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
<form action="${ctx}/view/updateDepartment.do" id="departForm" method="post">
    <!-- 隐藏表单，flag表示添加标记 -->
    <input type="hidden" name="flag" value="2">
    <input type="hidden" name="id" value="${department.id}">
    <table width="100%" border="0" cellpadding="0" cellspacing="10"
           class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="font3 fftd">
                <table>
                    <tr>
                        <td class="font3 fftd">专业名称：<input type="text" name="depart_name" id="depart_name" size="20"
                                                           value="${department.depart_name}"/></td>
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