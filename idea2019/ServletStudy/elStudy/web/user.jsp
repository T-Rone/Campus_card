<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<script type="text/javascript" >
    //全选
    function ckAll(){
        var flag=document.getElementById("checkAll").checked;
        var cks=document.getElementsByName("choose");
        for(let i=0; i<cks.length; i++){
            cks[i].checked=flag;
        }
    }
    //批量删除
    function delAll(){
        if(confirm("确定要删除吗？")){
        var cks=document.getElementsByName("choose");
        var str="";
        //拼接id
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                str+="id="+cks[i].value+"&";
            }
        }
        location.href="removeUser?"+str;
        }
    }
</script>
<body>
<h3>三国英雄人物表</h3>
<table border="1" cellpadding="15" cellspacing="0" bgcolor="#7fffd4">
    <tr>
        <th><input type="checkbox" name="checkAll" id="checkAll" onclick="ckAll()">全选/全不选</th>
        <th>编号</th>
        <th>名称</th>
        <th>密码</th>
        <th>操作&nbsp;&nbsp;&nbsp;<button><a href="addUser.jsp" style="text-decoration: none;">添加</a></button></th>
    </tr>
    <c:forEach items="${sessionScope.users}" var="user" varStatus="stat">
       <tr id="data_${stat.index}"  onMouseOver="move(this);" onMouseOut="out(this);">
            <td><input type="checkbox" name="choose" id="box_${stat.index}" value="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
           <td><a href="updateUser?id=${user.id}">
               修改</a>
               <a href="javascript:delAll()"  name="id" id="delete">删除</a>
<%--                <button onclick="doDel()" name="id" id="delete">删除</button>--%>
<%--               <a href="removeUser?id=${user.id}">删除</a>--%>
<%--               <a href="addUser.jsp">添加</a>--%>
           </td>
       </tr>
    </c:forEach>

<%--    <tr>--%>
<%--        <td>${sessionScope.users[0].id}</td>--%>
<%--        <td>${sessionScope.users[0].username}</td>--%>
<%--        <td>${sessionScope.users[0].password}</td>--%>
<%--    </tr>--%>
    </table>
</body>
</html>
