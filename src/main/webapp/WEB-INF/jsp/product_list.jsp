<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath}/home.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${category}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${booksize}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${pagebooks.books}" var="book">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/book/findbook?bookid=${book.bookId}"><img src="bookcover/101.jpg" width="115"
															height="129" border="0" /> </a>
													</p>
												</div>

												<div class="divlisttitle">
													<a href="product_info.jsp">
														书名:${book.bookName}
														<br />
														售价:${book.price}
													</a>
												</div>
											</td>
										</c:forEach>
									</tr>
								</table>

								<div class="pagination">
									<ul>

										<li class="disablepage">
											<a href="${pageContext.request.contextPath}/book/category?category=${category}&currentPage=${pagebooks.currentPage==1?1:pagebooks.currentPage-1}">
												&lt;&lt;上一页
											</a>
										</li>
										<li>
											第${pagebooks.currentPage }页/共${pagebooks.totalPage }页
										</li>
										<li class="nextPage">
											<a href="${pageContext.request.contextPath}/book/category?category=${category}&currentPage=${pagebooks.currentPage==pagebooks.totalPage?pagebooks.totalPage:pagebooks.currentPage+1}">
												下一页>>
											</a>
										</li>

									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
