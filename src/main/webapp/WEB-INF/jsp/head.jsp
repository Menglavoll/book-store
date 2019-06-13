<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td>
				<a href="home.jsp">
				<img src="images/logo.png" width="95" height="30" border="0" />
				</a>
			</td>
			<c:if test="${empty user && empty admin}">
				<td style="text-align:center">
					<img src="images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />&nbsp;
					<a href="#">帮助中心</a> | <a href="login.jsp">登录</a>
					| <a href="register.jsp">新用户注册</a> | <a href="adminlogin.jsp">管理员登录</a>
					| <a href="adminregister.jsp">管理员注册</a> | <a href="home.jsp">主页</a>
				</td>
			</c:if>
			<c:if test="${not empty user}">
				<td style="text-align:center">
					<img src="images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />&nbsp;
					<a href="/cart/searchcarts">购物车</a> | <a href="#">帮助中心</a>
					| <a href="myAccount.jsp">欢迎:${user.userName}</a>
					<a href="/user/unlogin">注销</a> | <a href="myAccount.jsp">我的账户</a>
					| <a href="home.jsp">主页</a>
				</td>
			</c:if>
			<c:if test="${not empty admin}">
				<td style="text-align:center">
					<img src="images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />&nbsp;
					<a href="/admin/login/home.jsp">欢迎管理员:${admin.adminName}</a>
					&nbsp;<a href="/admin/unlogin">注销</a>
				</td>
			</c:if>
		</tr>
	</table>
</div>