<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.tdragon.com" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>--%>
<html>
<head>
	<title>学生信息</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/bootstrap-responsive.css"/>
	<link rel="stylesheet" type="text/css" href="../jsUi/Css/style.css"/>
	<script type="text/javascript" src="../jsUi/Js/jquery.js"></script>
	<%--    <script type="text/javascript" src="../jsUi/Js/jquery.sorted.js"></script>--%>
	<script type="text/javascript" src="../jsUi/Js/bootstrap.js"></script>
	<script type="text/javascript" src="../jsUi/Js/ckform.js"></script>
	<script type="text/javascript" src="../jsUi/Js/common.js"></script>
	<script>
		function doDel() {
			var boxs = $("input[type='checkbox'][id^='box_']");

			/** 给全选按钮绑定点击事件  */
			$("#checkAll").click(function () {
				// this是checkAll  this.checked是true
				// 所有数据行的选中状态与全选的状态一致
				boxs.attr("checked", this.checked);
			});

			var checkedBoxs = boxs.filter(":checked");
			if (checkedBoxs.length < 1) {
				alert("请选择一个需要删除的学生！");
			} else {
				/** 得到 选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function () {
					return this.value;
				});

				if (confirm("确认要删除吗?")) {
					window.location = "${ctx}/view/removeStudent.do?id=" + ids.get();
				}

			}

		}

	</script>


	<style type="text/css">
		.font3 {
			text-align: center;
		}

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
<!-- 查询区  -->
<tr valign="left">
	<td height="30">
		<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr>
				<td class="fftd">
					<form method="post" action="${ctx}/view/selectStudent.do">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								学生姓名：<input type="text" name="name">
								<input type="submit" value="搜索"/>
								学号：<input type="text" name="usid">
								<input type="submit" value="搜索"/>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</td>
</tr>

<table class="table table-bordered table-hover definewidth m10">
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" id="checkAll"></th>
		<th>学号</th>
		<th>姓名</th>
		<th>学院</th>
		<th>专业</th>
		<th>班级</th>
		<th>性别</th>
		<th>籍贯</th>
		<th>出生日期</th>
		<th>入学时间</th>
		<th>操作</th>
	</tr>
	</thead>

	<tbody>
	<c:forEach items="${requestScope.students}" var="student" varStatus="stat">
		<tr id="data_${stat.index}" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
			<td><input type="checkbox" id="box_${stat.index}" value="${student.id}"></td>
			<td>${student.usid}</td>
			<td>${student.name}</td>
			<td>${student.college}</td>
			<td>${student.major}</td>
			<td>${student.classes}</td>
			<td>
				<c:choose>
					<c:when test="${student.sex == 1 }">男</c:when>
					<c:otherwise>女</c:otherwise>
				</c:choose>
			</td>

			<td>${student.home}</td>
			<td>${student.birth}</td>
			<td>${student.schoolday}</td>
			<td align="center" width="40px;"><a href="${ctx}/view/updateStudent.do?flag=1&id=${student.id}">
				修改</a>
				<a href='javascript:doDel()' name="id" id="delete">删除</a>
			</td>
		</tr>
	</c:forEach>


	</tbody>
</table>


<!-- 分页标签 -->
<table align="center" class="table-hover">
	<tr>
		<td align="center" class="font3"><fkjava:pager
				pageIndex="${requestScope.pageModel.pageIndex}"
				pageSize="${requestScope.pageModel.pageSize}"
				recordCount="${requestScope.pageModel.recordCount}"
				style="digg"
				submitUrl="${ctx}/view/selectStudent.do?pageIndex={0}"/>
		</td>
		<td></td>
	</tr>
</table>
</body>

</html>
