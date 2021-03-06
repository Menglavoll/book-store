<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />

</head>

<body class="main">
	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="home.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="cart.jsp">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单
					</div>

					<form id="orderForm" action="/order/create" method="post">
						<table cellspacing="0" class="infocontent">
							<tr>
								<td><table width="100%" border="0" cellspacing="0">
										<tr>
											<td><img src="images/buy2.gif" width="635" height="38" />
												<p>您好：${user.userName}！欢迎您来到商城结算中心</p>
											</td>
										</tr>
										<tr style="text-align: center">
											<td><table cellspacing="1" class="carttable">
													<tr>
														<td width="20%">序号</td>
														<td width="20%">商品名称</td>
														<td width="20%">价格</td>
														<td width="20%">数量</td>
														<td width="20%">小计</td>
													</tr>
												</table>
										<c:set var="sum" value="0" > </c:set>
											<c:forEach items="${cart }" var="entry" varStatus="vs">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="20%">${vs.count}</td>
														<td width="20%">${entry.key.bookName}</td>
														<td width="20%">${entry.key.price}</td>
														<td width="20%"><input name="text" type="text"
															value="${entry.value}" style="width:20px" readonly="readonly" /></td>
														<td width="20%">${entry.value*entry.key.price}</td>
													</tr>
												</table>
											<c:set var="sum" value="${sum+entry.value*entry.key.price }"> </c:set>
										</c:forEach>

												<table cellspacing="1" class="carttable">
													<tr>
														<td style="text-align:right; padding-right:40px;">
															<font style="color:#FF6600; font-weight:bold">
																total：&nbsp;&nbsp;
															<input disabled name="total" value="${sum}元">
															</font>
														</td>
													</tr>
												</table>

												<p>
													address：<input name="address" type="text" style="width:350px" />&nbsp;&nbsp;&nbsp;&nbsp;
													<span>请填写收获地址！</span><br />
													receiver：&nbsp;&nbsp;&nbsp;&nbsp;<input name="receiver" type="text" value="${user.userName}" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;
													<br/>
													mobile：<input type="text" name="mobile" value="${user.mobile}" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;
												</p>
												<hr />
												<p style="text-align:right">
													<input type="image" name="subimt" src="images/gif53_029.gif" width="204" height="51" border="0">
												</p>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>

	<jsp:include page="foot.jsp" />

</body>
</html>
