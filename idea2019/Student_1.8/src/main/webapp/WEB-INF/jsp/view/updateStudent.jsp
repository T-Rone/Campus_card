<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" %>
<html>
<head>
	<title></title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap-responsive.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/style.css"/>
	<script type="text/javascript" src="../jsUi/Js/jquery.js"></script>
	<script type="text/javascript" src="../jsUi/Js/jquery.sorted.js"></script>
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
<form action="${ctx}/view/updateStudent.do" id="studentForm" method="post">
	<!-- 隐藏表单，flag表示添加标记 -->
	<input type="hidden" name="flag" value="2">
	<input type="hidden" name="id" value="${student.id}">
	<table width="100%" border="0" cellpadding="0" cellspacing="10"
		   class="table table-bordered table-hover definewidth m10">
		<tr>
			<td class="font3 fftd">
				<table>
					<tr>
						<td class="font3 fftd">学号：<input type="text" name="usid" id="usid" size="20"
														 value="${student.usid}"/></td>
					</tr>
					<tr>
						<td>姓名：<input type="text" id="name" name="name" value="${student.name}"></td>
					</tr>
					<tr>
						<td>学院：<input type="text" id="college" name="college" value="${student.college}"></td>
					</tr>
					<tr>
						<td>专业：<input type="text" id="major" name="major" value="${student.major}"></td>
					</tr>
					<tr>
						<td>班级：<input type="text" id="classes" name="classes" value="${student.classes}"></td>
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
						<td>籍贯：<input type="text" id="home" name="home" value="${student.home}"></td>
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
