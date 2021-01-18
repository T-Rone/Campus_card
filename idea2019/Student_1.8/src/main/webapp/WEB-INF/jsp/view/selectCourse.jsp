<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.tdragon.com" prefix="c" %>

<html>
<head>
	<title>课程信息</title>
	<meta charset="UTF-8">
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
				alert("请选择一个需要删除的专业！");
			} else {
				/** 得到 选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function () {
					return this.value;
				});

				if (confirm("确认要删除吗?")) {
					window.location = "${ctx}/view/removeCourse.do?id=" + ids.get();
				}

			}

		}

	</script>

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
<!-- 查询区  -->
<tr valign="left">
	<td height="30">
		<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr>
				<td class="fftd">
					<form name="courseform" method="post" id="courseform" action="${ctx}/view/selectCourse.do">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="font3">
									课程名称：<input type="text" name="cname">
									<input type="submit" value="搜索"/>
								</td>
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
		<th>课程号</th>
		<th> 课程名称</th>
		<th>开课学期</th>
		<th>学时</th>
		<th>绩点</th>
		<th>操作</th>
	</tr>
	</thead>

	<tbody>
	<c:forEach items="${requestScope.courses}" var="course" varStatus="stat">
		<tr id="data_${stat.index}" class="main_trbg">
				<%--					onMouseOver="move(this)" onMouseOut="out(this)"--%>
			<td><input type="checkbox" id="box_${stat.index}" value="${course.id}"></td>
			<td>${course.cid}</td>
			<td>${course.cname}</td>
			<td>${course.term}</td>
			<td>${course.period}</td>
			<td>${course.credit}</td>
			<td align="center" width="40px;"><a href="${ctx}/view/updateCourse.do?flag=1&id=${course.id}">
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
				submitUrl="${ctx}/view/selectCourse.do?pageIndex={0}"/>
		</td>
		<td></td>
	</tr>
</table>
</body>
</html>
