<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" %>
<html>
<head>
	<title>增加学生信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap-responsive.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/style.css"/>
	<script type="text/javascript" src="../jsUi/Js/jquery.js"></script>
	<%--    <script type="text/javascript" src="../jsUi/Js/jquery.sorted.js"></script>--%>
	<script type="text/javascript" src="../jsUi/Js/bootstrap.js"></script>
	<script type="text/javascript" src="../jsUi/Js/ckform.js"></script>
	<script type="text/javascript" src="../jsUi/Js/common.js"></script>
	<script type="text/javascript" src="../jsUi/Js/My97DatePicker/WdatePicker.js"></script>

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

	<form action="${ctx}/view/insertStudent.do" method="post" id="studentForm">
		<table>
			<input type="hidden" name="flag" value="2">
			<tr>
				<td>学号：<input type="text" id="usid" name="usid"></td>
			</tr>
			<tr>
				<td>姓名：<input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td>学院：<input type="text" id="college" name="college"></td>
			</tr>
			<tr>
				<td>专业：<input type="text" id="major" name="major"></td>
			</tr>
			<tr>
				<td>班级：<input type="text" id="classes" name="classes"></td>
			</tr>

			<tr>
				<td class="font3 fftd">性别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="sex" style="width:150px;">
						<option value="0">--请选择性别--</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</td>
			</tr>

			<tr>
				<td>籍贯：<input type="text" id="home" name="home"></td>
			</tr>
			<tr>
				<td class="font3 fftd">
					出生日期：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
								name="birth" id="birth" size="20"/>
				</td>
			</tr>

			<tr>
				<td class="font3 fftd">
					入学时间：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
								name="schoolday" id="schoolday" size="20"/>
				</td>
			</tr>


		</table>
		<input type="submit" value="增加">
		<input type="reset" value="重置">
	</form>
</center>
</body>
</html>

