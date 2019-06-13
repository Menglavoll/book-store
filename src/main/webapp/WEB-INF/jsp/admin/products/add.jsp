<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">

</HEAD>
<script type="text/javascript">
	window.onload = function () {
		document.getElementById("bookname").onblur =function () {
			var bookname = this.value;
			var msg_bookname = document.getElementById("msg_bookname");

			if (bookname == null || bookname == ""){
				document.getElementById("add").setAttribute("disabled","true");
				msg_bookname.innerHTML = "<font color='red'>书名不能为空！</font>";
			} else {
				document.getElementById("add").removeAttribute("disabled");
			}

		}
		document.getElementById("price").onblur = function () {
			var price = this.value;
			var msg_price = document.getElementById("msg_price");
			if (price == null || price == ""){
				document.getElementById("add").setAttribute("disabled","true");
				msg_price.innerHTML = "<font color='red'>价格不能为空！</font>";
			} else {
				document.getElementById("add").removeAttribute("disabled");
			}
		}
		document.getElementById("pnum").onblur = function () {
			var pnum = this.value;
			var msg_pnum = document.getElementById("msg_pnum");
			if (pnum == null || pnum == ""){
				document.getElementById("add").setAttribute("disabled","true");
				msg_pnum.innerHTML = "<font color='red'>库存不能为空！</font>";
			} else {
				document.getElementById("add").removeAttribute("disabled");
			}
		}
		document.getElementById("category").onblur = function () {
			var category = this.value;
			var msg_category = document.getElementById("msg_category");
			if (category == null || category == ""){
				document.getElementById("add").setAttribute("disabled","true");
				msg_category.innerHTML = "<font color='red'>类别未选！</font>";
			} else {
				document.getElementById("add").removeAttribute("disabled");
			}
		}
	}
</script>
<body>
	<form id="userAction_save_do" name="Form1"
		action="/book/addbook" method="post"
		enctype="multipart/form-data">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加商品</STRONG> </strong>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
				<td class="ta_01" bgColor="#ffffff">
					<input id="bookname" type="text" name="name" class="bg"/>
					<span id="msg_bookname"></span>
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品价格：</td>
				<td class="ta_01" bgColor="#ffffff">
					<input id="price" type="text" name="price" class="bg" />
					<span id="msg_price"></span>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品数量：</td>
				<td class="ta_01" bgColor="#ffffff">
					<input id="pnum" type="text" name="pnum" class="bg" />
					<span id="msg_pnum"></span>
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品类别：</td>
				<td class="ta_01" bgColor="#ffffff">
					<select name="category" id="category">
						<option value="" selected="selected">--选择商品类加--</option>
						<option value="文学">文学</option>
						<option value="生活">生活</option>
						<option value="计算机">计算机</option>
						<option value="外语">外语</option>
						<option value="经营">经营</option>
						<option value="励志">励志</option>
						<option value="社科">社科</option>
						<option value="学术">学术</option>
						<option value="少儿">少儿</option>
						<option value="艺术">艺术</option>
						<option value="原版">原版</option>
						<option value="科技">科技</option>
						<option value="考试">考试</option>
						<option value="生活百科">生活百科</option>
					</select>
					<span id="msg_category"></span>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品图片：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<input
					type="file" name="upload" size="30" value=""/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">商品描述：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<textarea
						name="description" cols="30" rows="3" 
						style="WIDTH: 96%"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">

					<input id="add" type="submit" class="button_ok" value="确定">
						
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>

					<input type="reset" value="重置" class="button_cancel">

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<input class="button_ok" type="button" onclick="history.go(-1)" value="返回" >
					<span id="Label1">
					
					</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>