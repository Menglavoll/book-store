<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
						<a href="${pageContext.request.contextPath}/home.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:${order.orderPackage}</p></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align: center" width="15%">序号</td>
													<td style="text-align: center" width="25%">商品名称</td>
													<td style="text-align: center" width="20%">价格</td>
													<td style="text-align: center" width="20%">数量</td>
													<td style="text-align: center" width="20%">小计</td>

												</tr>
											</table>
											<table width="100%" border="0" cellspacing="0">
												<c:forEach items="${packages}" var="packagelist" varStatus="vs">
													<tr>
														<td style="text-align: center" width="15%">${vs.count}</td>
														<td style="text-align: center" width="25%">${packagelist.bookName}</td>
														<td style="text-align: center" width="20%">${packagelist.price}</td>
														<td style="text-align: center" width="20%">${packagelist.num}</td>
														<td style="text-align: center" width="20%">${packagelist.count}</td>
													</tr>
												</c:forEach>
											</table>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">合计：&nbsp;&nbsp;${order.total}</font></td>
												</tr>
											</table>

											<p>
												收货地址：${order.address}&nbsp;&nbsp;&nbsp;&nbsp;<br />
												收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiver}&nbsp;&nbsp;&nbsp;&nbsp;<br />
												联系方式：${order.recMobile}&nbsp;&nbsp;&nbsp;&nbsp;

											</p>
											<hr>
											<p style="text-align:right">
												<a href="pay.jsp"><img src="images/gif53_029.gif" width="204"
													height="51" border="0" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</td>

						</tr>

					</table>
				</td>
			</tr>
		</table>
	</div>

	<jsp:include page="foot.jsp" />

</body>
</html>
