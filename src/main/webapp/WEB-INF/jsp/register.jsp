<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function changeImage() {
		document.getElementById("img").src = "${pageContext.request.contextPath}/check/code?time="
				+ new Date().getTime();
	}

	window.onload = function () {
	    // 验证邮箱
		document.getElementById("email").onblur = function () {
			var email = this.value;
			var msg_email = document.getElementById("msg_email");

			if (email == null || email == ""){
				document.getElementById("register").setAttribute("disabled","true");
				msg_email.innerHTML = "<font color='red'>邮箱不能为空！</font>";
			} else if (email.indexOf("@") < 0 || email.indexOf(".") < 0){
				document.getElementById("register").setAttribute("disabled","true");
				msg_email.innerHTML = "<font color='red'>邮箱格式不正确！</font>";
			} else {
				document.getElementById("register").removeAttribute("disabled");
				msg_email.innerHTML = "<font color='green'>√</font>";
			}
		}

		// 验证手机号
		document.getElementById("mobile").onblur = function () {
			var mobile = this.value;
			var msg_mobile = document.getElementById("msg_mobile");

			if(mobile == null || mobile == ""){
                document.getElementById("register").setAttribute("disabled","true");
                msg_mobile.innerHTML = "<font color='red'>手机号不能为空！</font>";
            } else if(!(/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3|7])|(18([0-3]|[5-9])))\d{8}$/.test(mobile))){
                document.getElementById("register").setAttribute("disabled","true");
                msg_mobile.innerHTML = "<font color='red'>手机号格式不正确！</font>";
			} else {
                var xhr_mobile = new XMLHttpRequest();
                xhr_mobile.onreadystatechange = function () {
                    if (xhr_mobile.readyState == 4){
                        if (xhr_mobile.status == 200){
                            if (xhr_mobile.responseText == 1){
                                document.getElementById("register").removeAttribute("disabled");
                                msg_mobile.innerHTML = "<font color='green'>√</font>";
                            } else if(xhr_mobile.responseText == 0){
                                document.getElementById("register").setAttribute("disabled","true");
                                msg_mobile.innerHTML = "<font color='red'>该手机号已被使用，请直接登录！</font>";
                            } else {
                                document.getElementById("register").setAttribute("disabled","true");
                                msg_mobile.innerHTML = "<font color='red'>手机号验证出现未知错误，请重试！</font>";
                            }
                        }
                    }
                }
                xhr_mobile.open("post","/check/ckmobile",true);
                xhr_mobile.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                xhr_mobile.send("mobile="+mobile);
            }
		}

		// 验证昵称
        document.getElementById("username").onblur = function () {
            var username = this.value;
            var msg_username = document.getElementById("msg_username");

            if (username.length < 4 || username == "" || username == null){
                document.getElementById("register").setAttribute("disabled","true");
                msg_username.innerHTML = "<font color='red'>昵称至少4位！<font>";
            }else {
                document.getElementById("register").removeAttribute("disabled");
                msg_username.innerHTML = "<font color='green'>√<font>";
            }
        }

        // 验证密码
        document.getElementById("pwd").onblur = function () {
            var pwd = this.value;
            var msg_pwd = document.getElementById("msg_pwd");
            if (pwd == null || pwd == "" || pwd.length<6) {
                document.getElementById("register").setAttribute("disabled","true");
                msg_pwd.innerHTML = "<font color='red'>密码至少6位！</font>";
            } else {
                document.getElementById("register").removeAttribute("disabled");
                msg_pwd.innerHTML = "<font color='green'>√</font>";
            }
        }

        // 验证确认密码
        document.getElementById("repwd").onblur = function () {
            var repwd = this.value;
            var pwd = document.getElementById("pwd");
            var msg_repwd = document.getElementById("msg_repwd");
            if(repwd.length < 6 || repwd != pwd.value){
                document.getElementById("register").setAttribute("disabled","true");
                msg_repwd.innerHTML = "<font color='red'>两次输入密码不一致！</font>";
            } else {
                document.getElementById("register").removeAttribute("disabled");
                msg_repwd.innerHTML = "<font color='green'>√</font>";
            }
        }

        // 验证码
        document.getElementById("ckcode").onblur = function () {
            var ckcode = this.value;
            var msg_ckcode = document.getElementById("msg_ckcode");

            if (ckcode == null || ckcode == ""){
                document.getElementById("register").setAttribute("disabled","true");
                msg_ckcode.innerHTML = "<font color='red'>×</font>";
            } else {
                var xhr_ckcode = new XMLHttpRequest();
                xhr_ckcode.onreadystatechange = function () {
                    if (xhr_ckcode.readyState == 4){
                        if(xhr_ckcode.status == 200){
                            if(xhr_ckcode.responseText == 1){
                                document.getElementById("register").removeAttribute("disabled");
                                msg_ckcode.innerHTML = "<font color='green'>√</font>";
                            } else {
                                document.getElementById("register").setAttribute("disabled","true");
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
	<%@include file="head.jsp"%>
	<%--导入头 --%>
	<%@include file="menu_search.jsp"%><%--导入导航条与搜索 --%>

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/user/register"
<%--		<form action="/user/register"--%>
			method="post">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>新会员注册 ${msg_register }</h1>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">邮箱：</td>
								<td style="width:40%">
								<input id="email" type="text" class="textinput"
									name="email" /></td>
								<td style="text-align:left; width:20%"><span id="msg_email">请输入有效的邮箱地址</span></td>
							</tr>
							<tr>
								<td style="text-align:right; width:20%">手机号：</td>
								<td style="width:40%">
									<input id="mobile" type="text" class="textinput"
										   name="mobile" /></td>
								<td style="text-align:left; width:20%">
                                    <span id="msg_mobile">请输入有效的11为手机号</span>
                                </td>
							</tr>
							<tr>
								<td style="text-align:right">昵称：</td>
								<td>
									<input id="username" type="text" class="textinput" name="username" />
								</td>
								<td style="text-align:left; width:20%">
                                    <span id="msg_username">昵称设置至少4位！</span>
                                </td>
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td>
                                    <input id="pwd" type="password" class="textinput" name="password" />
                                </td>
								<td style="text-align:left; width:20%">
                                    <span id="msg_pwd">密码设置至少6位</span>
                                </td>
							</tr>
							<tr>
								<td style="text-align:right">确认密码：</td>
								<td>
                                    <input id="repwd" type="password" class="textinput" name="repassword" />
                                </td>
								<td style="text-align:left; width:20%">
                                    <span id="msg_repwd">请再次输入密码</span>
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
                                    <textarea class="textarea" name="introduce"></textarea>
								</td>
							</tr>

						</table>

						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
                            <tr>
                                <td style="text-align:right;width:20%;">&nbsp;</td>
                                <td colspan="2" style="width:50%"><img
                                        src="${pageContext.request.contextPath}/check/code" width="180"
                                        height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
                                    <a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
                                </td>
                            </tr>
							<tr>
								<td style="text-align:right; width:20%">输入验证码：</td>
								<td style="width:50%">
                                    <input id="ckcode" type="text" class="textinput" name="ckcode"/>
								</td>
								<td style="text-align:left; width:20%">
                                    <span id="msg_ckcode"></span>
                                </td>
							</tr>
						</table>

						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center">
									<input id="register" type="image" src="images/signup.gif" name="submit" border="0"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%">
                    <img src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" />
                </td>
				<td style="padding-top:5px; padding-left:50px">
                    <a href="#">
                        <font color = "#747556"><b>CONTACT US</b></font>
                    </a>
                </td>
			</tr>
			<tr>
				<td style="padding-left:50px">
                    <font color="#CCCCCC">
                        <b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b>
                    </font>
                </td>
			</tr>
		</table>
	</div>
</body>
</html>
