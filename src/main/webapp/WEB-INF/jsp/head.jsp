<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td><a href="home.jsp"><img src="images/logo.png"
                                        width="95" height="30" border="0" /> </a></td>
			<c:if test="${empty u}">
				<td style="text-align:right"><img src="images/cart.gif"
												  width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a
						href="cart.jsp">购物车</a> | <a href="#">帮助中心</a> | <a href="login.jsp">登录</a>
					| <a href="register.jsp">新用户注册</a> | <a href="adminlogin.jsp">管理员登录</a> | <a href="home.jsp">主页</a>
				</td>
			</c:if>
			<c:if test="${ not empty u}">
				<td style="text-align:right"><img src="images/cart.gif"
												  width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a
						href="cart.jsp">购物车</a> | <a href="#">帮助中心</a>
					| <a href="myAccount.jsp">欢迎:${u.userName}</a> &nbsp;
					<a href="/user/unlogin">注销</a> | <a href="cart.jsp">购物车</a> | <a href="home.jsp">主页</a>
				</td>
			</c:if>
		</tr>
	</table>
</div>