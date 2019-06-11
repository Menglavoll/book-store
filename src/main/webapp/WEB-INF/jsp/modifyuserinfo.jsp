<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<script type="text/javascript">
	function changeImage() {
		document.getElementById("img").src = "${pageContext.request.contextPath}/check/code?time="
				+ new Date().getTime();
	}

	window.onload = function () {
		// 验证昵称
		document.getElementById("username").onblur = function () {
			var username = this.value;
			var msg_username = document.getElementById("msg_username");

			if (username.length < 4 || username == "" || username == null){
				document.getElementById("update").setAttribute("disabled","true");
				msg_username.innerHTML = "<font color='red'>昵称至少4位！<font>";
			}else {
				document.getElementById("update").removeAttribute("disabled");
				msg_username.innerHTML = "<font color='green'>√<font>";
			}
		}

		// 验证密码
		document.getElementById("pwd").onblur = function () {
			var pwd = this.value;
			var msg_pwd = document.getElementById("msg_pwd");
			if (pwd == null || pwd == "" || pwd.length<6) {
				document.getElementById("update").setAttribute("disabled","true");
				msg_pwd.innerHTML = "<font color='red'>密码至少6位！</font>";
			} else {
				document.getElementById("update").removeAttribute("disabled");
				msg_pwd.innerHTML = "<font color='green'>√</font>";
			}
		}

		// 验证确认密码
		document.getElementById("repwd").onblur = function () {
			var repwd = this.value;
			var pwd = document.getElementById("pwd");
			var msg_repwd = document.getElementById("msg_repwd");
			if(repwd.length < 6 || repwd != pwd.value){
				document.getElementById("update").setAttribute("disabled","true");
				msg_repwd.innerHTML = "<font color='red'>两次输入密码不一致！</font>";
			} else {
				document.getElementById("update").removeAttribute("disabled");
				msg_repwd.innerHTML = "<font color='green'>√</font>";
			}
		}

		// 验证码
		document.getElementById("ckcode").onblur = function () {
			var ckcode = this.value;
			var msg_ckcode = document.getElementById("msg_ckcode");

			if (ckcode == null || ckcode == ""){
				document.getElementById("update").setAttribute("disabled","true");
				msg_ckcode.innerHTML = "<font color='red'>×</font>";
			} else {
				var xhr_ckcode = new XMLHttpRequest();
				xhr_ckcode.onreadystatechange = function () {
					if (xhr_ckcode.readyState == 4){
						if(xhr_ckcode.status == 200){
							if(xhr_ckcode.responseText == 1){
								document.getElementById("update").removeAttribute("disabled");
								msg_ckcode.innerHTML = "<font color='green'>√</font>";
							} else {
								document.getElementById("update").setAttribute("disabled","true");
								msg_ckcode.innerHTML = "<font color='red'>×</font>";
							}
						}
					}
				}

				xhr_ckcode.open("post","/check/ckcode",true);
				xhr_ckcode.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
				xhr_ckcode.send("ckcode="+ckcode);
			}
		}
	}
</script>

<body class="main">
	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0"
						style="margin-top:30px">
						<tr>
							<td class="listtitle">我的帐户</td>
						</tr>
						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="modifyuserinfo.jsp">用户信息修改</a></td>
						</tr>

						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="orderlist.jsp">订单查询</a>
							</td>
						</tr>

						<tr>
							<td class="listtd"><img src="images/miniicon.gif" width="9"
								height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/user/unlogin">用戶退出</a>
							</td>
						</tr>
					</table></td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="home.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="myAccount.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<form action="/user/updateuser" method="post">
									<input type="hidden" name="id" value="${u.userId}">
									<table width="100%" border="0" cellspacing="2" class="upline">
										<tr>
											<td style="text-align:right; width:20%">手机号：</td>
											<td style="width:40%; padding-left:20px">
												<p>${user.mobile}</p>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right; width:20%">邮箱：</td>
											<td style="width:40%; padding-left:20px">
												<p>${user.email}</p>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td style="text-align:right">修改昵称：</td>
											<td>
												<input id="username" type="text" name="username"
													   value="${user.userName}" class="textinput" onclick="clearText"/>
											</td>
											<td>
												<span id="msg_username">昵称至少4位</span>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">修改密码：</td>
											<td>
												<input id="pwd" type="password" name="password" class="textinput" />
											</td>
											<td>
												<span id="msg_pwd">密码设置至少6位，请区分大小写</span>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">重复密码：</td>
											<td>
												<input id="repwd" type="password" class="textinput" name="repassword"/>
											</td>
											<td>
												<span id="msg_repwd"></span>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">性别：</td>
											<td colspan="2">&nbsp;&nbsp;
												<input type="radio" name="gender" value="男" checked="checked" /> 男
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="radio" name="gender" value="女" /> 女
											</td>
										</tr>
										<tr>
											<td style="text-align:right">个人介绍：</td>
											<td colspan="2">
												<textarea name="introduce" type="text" value="${user.introduce}" class="textinput" ></textarea>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">&nbsp;</td>
											<td ><img
													src="${pageContext.request.contextPath}/check/code" width="180"
													height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
												<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">输入验证码：</td>
											<td>
												<input id="ckcode" type="text" class="textinput" name="ckcode"/>
											</td>
											<td>
												<span id="msg_ckcode"></span>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>

									<p style="text-align:center">
										<input id="update" type="image" src="images/botton_gif_025.gif" border="0" name="submit">
									</p>
									<p style="text-align:center">&nbsp;</p>
								</form>
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
