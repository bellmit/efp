<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath = request.getContextPath();%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-1.11.1.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
 <div class="col-lg-10">
     <div class="ibox float-e-margins">
	<table class="table table-striped table-bordered table-hover">
		<thead>
                <tr>
                        <th class="text-center">序号</th>
                        <th class="text-center">用户名</th>
                        <th class="text-center">用户所属地区</th>
                        <th class="text-center">是否有效</th>
                        <th class="text-center">注册时间</th>
                        <th class="text-center">操作</th>
                </tr>
        </thead>
        <tbody>
              <c:forEach items="${userList }" var="user" varStatus="u">
               <c:if test="${!(sessionInfo.userName != admin && user.userName == admin)}">
                   <tr>
                       <td class="text-center">${u.index+1}</td>
                       <td class="text-center">${user.userName}</td>
                       <td class="text-center">
                           <c:if test="${user.userType==0}">北京</c:if>
                           <c:if test="${user.userType==1}">上海</c:if>
                       </td>
                       <td class="text-center">
                           <c:if test="${user.islock==false}">是</c:if>
                           <c:if test="${user.islock==true}">否</c:if>
                       </td>
                       <td class="text-center">
                           <fmt:formatDate value="${user.regTime}" pattern="yyyy-MM-dd" ></fmt:formatDate>
                       </td>
                       <td class="text-center">
                       <a href="javascript:" class="btn btn-xs btn-info" onclick="location.href='<%=basePath%>/user/update?id=${user.id}';">编辑</a>
                       <%-- <c:if test="${user.userName != sessionInfo.userName}">
                           <a href="javascript:" class="btn btn-xs btn-danger" onclick="deleteUser(${user.id});">删除</a>
                       </c:if> --%>
                   </tr>
               </c:if>
              </c:forEach>
          </tbody>
	</table>
</div>
</div>
</div>
</body>
</html>