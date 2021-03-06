<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>

<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript">
		function changeImage() {
			document.getElementById("img").src = "${pageContext.request.contextPath}/check/code?time="
					+ new Date().getTime();
		}
		window.onload = function () {
			// 验证手机号
			document.getElementById("mobile").onblur = function () {
				var mobile = this.value;
				var msg_mobile = document.getElementById("msg_mobile");

				if(mobile == null || mobile == ""){
					document.getElementById("login").setAttribute("disabled","true");
					msg_mobile.innerHTML = "<font color='red'>×</font>";
				} else if(!(/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3|7])|(18([0-3]|[5-9])))\d{8}$/.test(mobile))){
					document.getElementById("login").setAttribute("disabled","true");
					msg_mobile.innerHTML = "<font color='red'>×</font>";
				} else {
					var xhr_mobile = new XMLHttpRequest();
					xhr_mobile.onreadystatechange = function () {
						if (xhr_mobile.readyState == 4){
							if (xhr_mobile.status == 200){
								if (xhr_mobile.responseText == 0){
									document.getElementById("login").removeAttribute("disabled");
									msg_mobile.innerHTML = "<font color='green'>√</font>";
								} else {
									document.getElementById("login").setAttribute("disabled","true");
									msg_mobile.innerHTML = "<font color='red'>×</font>";
								}
							}
						}
					}
					xhr_mobile.open("post","/check/ckmobile",true);
					xhr_mobile.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
					xhr_mobile.send("mobile="+mobile);
				}
			}

			// 验证密码
			document.getElementById("pwd").onblur = function () {
				var pwd = this.value;
				var msg_pwd = document.getElementById("msg_pwd");
				if (pwd == null || pwd == "" || pwd.length < 6) {
					document.getElementById("login").setAttribute("disabled","true");
					msg_pwd.innerHTML = "<font color='red'>×</font>";
				} else {
					document.getElementById("login").removeAttribute("disabled");
					msg_pwd.innerHTML = "<font color='green'>√</font>";
				}
			}

			// 验证图片验证码
			document.getElementById("ckcode").onblur = function () {
				var ckcode = this.value;
				var msg_ckcode = document.getElementById("msg_ckcode");

				if (ckcode == null || ckcode == ""){
					document.getElementById("login").setAttribute("disabled","true");
					msg_ckcode.innerHTML = "<font color='red'>×</font>";
				} else {
					var xhr_ckcode = new XMLHttpRequest();
					xhr_ckcode.onreadystatechange = function () {
						if (xhr_ckcode.readyState == 4){
							if(xhr_ckcode.status == 200){
								if(xhr_ckcode.responseText == 1){
									document.getElementById("login").removeAttribute("disabled");
									msg_ckcode.innerHTML = "<font color='green'>√</font>";
								} else {
									document.getElementById("login").setAttribute("disabled","true");
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
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divcontent">
		<form action="${pageContext.request.contextPath }/user/login" method="post">
			<table width="900px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<div style="height:470px">
							<b>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</b>
							<div>
								<table width="85%" border="0" cellspacing="0">
									<tr>
										<td>
											<div id="logindiv">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td style="text-align:center; padding-top:20px">
															<img src="images/logintitle.gif" width="150" height="30" />
														</td>
													</tr>
													<tr>
														<td style="text-align:center;padding-top:20px;">
															<font color="#ff0000">${requestScope["user_msg"]}</font>
														</td>
													</tr>
													<tr>
														<td style="text-align:center">
															<form action="${pageContext.request.contextPath}/user/login">
															<table width="80%" border="0" cellspacing="0"
																style="margin-top:15px ;margin-left:auto; margin-right:auto">
																<h1>登录校验</h1>
																<p>${pwd_msg}</p>
																<tr>
																	<td style="text-align:right; padding-top:5px; width:5%">手机号：</td>
																	<td style="text-align:left">
																		<input id="mobile" name="mobile" type="text" class="textinput" />
																	</td>
																	<td style="text-align:left; padding-top:5px; width:25%">
																		<span id="msg_mobile"></span>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
																	<td style="text-align:left">
																		<input id="pwd" name="password" type="password" class="textinput" />
																	</td>
																	<td style="text-align:left; padding-top:5px">
																		<span id="msg_pwd"></span>
																	</td>
																</tr>

																<tr>
																	<td style="text-align:right;width:5%">&nbsp;</td>
																	<td colspan="2" style="width:40%">
																		<img src="${pageContext.request.contextPath}/check/code" width="180"
																			height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;<br/>
																		&nbsp;&nbsp;
																		<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">验证码：</td>
																	<td style="text-align:left">
																		<input id="ckcode" name="ckcode" type="text" class="textinput" />
																	</td>
																	<td style="text-align:left; padding-top:5px">
																		<span id="msg_ckcode"></span>
																	</td>
																</tr>

																<tr>
																	<td colspan="2" style="text-align:center">
																		<input type="checkbox" name="checkbox" value="checkbox" />
																		记住用户名&nbsp;&nbsp;
																		<input type="checkbox" name="checkbox" value="checkbox" />
																		自动登录
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top:10px; text-align:center">
																		<input id="login" name="submit" type="image"
																		src="images/loginbutton.gif" width="83" height="22" />
																	</td>
																</tr>

																<tr>
																	<td colspan="2" style="padding-top:10px">
																		<img src="images/loginline.gif" width="241" height="10" />
																	</td>
																</tr>
																<tr>
																	<td colspan="2" style="padding-top:10px; text-align:center">
																		<a href="register.jsp">
																			<img name="image" src="images/signupbutton.gif" width="135" height="33" />
																		</a>
																	</td>
																</tr>
															</table>
															</form>
														</td>
													</tr>
												</table>
											</div></td>
										<td style="text-align:left; padding-top:30px; width:40%"><h1>您还没有注册？</h1>
											<p>注册新会员，享受更优惠价格!</p>
											<p>千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。</p>
											<p>超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。</p>
											<p style="text-align:right">
												<a href="register.jsp"><img
													src="images/signupbutton.gif" width="135" height="33" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="foot.jsp" />
</body>
</html>
