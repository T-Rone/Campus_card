<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.tdragon.com" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>用户个人信息</title>
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
				alert("请选择一个需要删除的用户！");
			} else {
				/** 得到 选中的所有的需要删除的ids */
				var ids = checkedBoxs.map(function () {
					return this.value;
				});

				if (confirm("确认要删除吗?")) {
					window.location = "${ctx}/view/removeUser.do?id=" + ids.get();
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
<tr valign="left">
	<td height="30">
		<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr>
				<td class="fftd">
					<form name="userform" method="post" id="userform" action="${ctx}/view/selectUser.do">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="font3">
									用户名：<input type="text" name="name">
									<input type="submit" value="搜索"/>
									&nbsp;&nbsp;当前用户：[${sessionScope.user_session.name}]
									<a href="${ctx}/logout.do" id="exit">有趣退出</a>
									<%-- 这个退出是在子页面退出  就是比较有趣， --%>
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
		<th>用户名/账号名</th>
		<th>密码</th>
		<th>照片</th>
		<th>操作</th>
	</tr>
	</thead>

	<tbody>
	<c:forEach items="${requestScope.users}" var="user" varStatus="stat">
		<tr id="data_${stat.index}" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
			<td><input type="checkbox" id="box_${stat.index}" value="${user.id}"></td>
			<td>${user.name}</td>
			<td>${user.password}</td>
			<td>
				<img src="${pageContext.request.contextPath}/${user.image}" alt="${user.image}" name="image"
					 width="10%">
			</td>
			<td align="center" width="40px;"><a href="${ctx}/view/updateUser.do?flag=1&id=${user.id}">
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
				submitUrl="${ctx}/view/selectUser.do?pageIndex={0}"/>
		</td>
		<td></td>
	</tr>
</table>
</body>

</html>
