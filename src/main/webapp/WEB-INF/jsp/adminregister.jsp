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
    </script>
    <script type="text/javascript">

        window.onload=function(){

            // 获取手机号的标签
            var mobileElement = document.getElementById("mobile");

            // 获取邮箱
            var emailElement = document.getElementById("email");

            // 获取昵称
            var nicknameElement = document.getElementById("nickname");

            // 获取密码
            var pwdElement = document.getElementById("pwd");

            // 获取确认密码
            var repwdElement = document.getElementById("repwd");

            // 获取图片验证码
            var imgcodeElement = document.getElementById("imgcode");

            // 验证图片验证码
            imgcodeElement.onblur = function(){
                var imgcode = this.value;

                var msg_imgcode = document.getElementById("msg_imgcode");
                if (imgcode == null || imgcode == ''){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_imgcode.innerHTML = "<font color='red'>验证码不能为空！</font>";
                    return;
                }

                var img_xhr = new XMLHttpRequest();
                img_xhr.onreadystatechange = function () {
                    if(img_xhr.readyState==4) {//请求一切正常
                        if (img_xhr.status == 200) {//服务器响应一切正常

                            if (img_xhr.responseText==1){
                                document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                                msg_imgcode.innerHTML = "<font color='green'>√</font>";
                            } else if (img_xhr.responseText==0){
                                document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_imgcode.innerHTML = "<font color='red'>×</font>";
                            }
                        }
                    }
                }
                img_xhr.open("post","/check/ckcode",true);
                img_xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                img_xhr.send("ckcode="+imgcode);
            }

            // 验证昵称
            nicknameElement.onblur = function(){
                var nickname = this.value;

                var msg_nickname = document.getElementById("msg_nickname");
                if(nickname == null || nickname == ''){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_nickname.innerHTML = "<font color='red'>昵称不能为空！</font>";
                }else if(nickname.length < 4){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_nickname.innerHTML = "<font color='red'>昵称长度至少为4位！</font>";
                }else {
                    document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                    msg_nickname.innerHTML = "<font color='green'>√</font>";
                }
            }

            // 验证邮箱
            emailElement.onblur = function(){
                var email = this.value;

                var msg_email = document.getElementById("msg_email");
                if(email == null || email == ''){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_email.innerHTML = "<font color='red'>邮箱不能为空！</font>";
                }else if (email.indexOf('@') < 0 || email.indexOf('.') < 0 ) {
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_email.innerHTML = "<font color='red'>邮箱格式不正确！</font>";
                }else {
                    document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                    msg_email.innerHTML = "<font color='green'>√</font>";
                }
            }

            // 验证密码
            pwdElement.onblur = function() {
                var pwd = this.value;

                var msg_pwd = document.getElementById("msg_pwd");
                if(pwd.length<6){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_pwd.innerHTML = "<font color='red'>密码长度不足6位！</font>";
                }else {
                    document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                    msg_pwd.innerHTML = "<font color='green'>√</font>";
                }
            }

            // 验证确认密码
            repwdElement.onblur = function() {
                var repwd = this.value;
                var pwd = document.getElementById("pwd");

                var msg_repwd = document.getElementById("msg_repwd");
                if(pwd.value.length<6){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_repwd.innerHTML = "<font color='red'>密码长度不足6位！</font>";
                }else if (repwd != pwd.value) {
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_repwd.innerHTML = "<font color='red'>两次输入密码不一致！</font>";
                } else {
                    document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                    msg_repwd.innerHTML = "<font color='green'>√</font>";
                }
            }

            // 判断手机号是否可以使用
            mobileElement.onblur = function(){
                var mobile = this.value;//this等价于nameElement

                var msg_mobile = document.getElementById("msg_mobile");
                if (mobile == null || mobile == ''){
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_mobile.innerHTML = "<font color = 'red'>手机号不能为空！</font>";
                    return;
                } else if (!(/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3|7])|(18([0-3]|[5-9])))\d{8}$/.test(mobile))) {
                    document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_mobile.innerHTML = "<font color = 'red'>手机号格式不正确！</font>";
                    return;
                }

                //创建XMLHttpRequest对象
                var xhr = new XMLHttpRequest();
                //处理结果
                xhr.onreadystatechange = function(){
                    if(xhr.readyState==4){//请求一切正常
                        if(xhr.status==200){//服务器响应一切正常

                            if(xhr.responseText==1){
                                document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_mobile.innerHTML =  "<font color='red'>改手机号还未注册！</font>";
                            }else if(xhr.responseText==0){
                                document.getElementById("register").removeAttribute("disabled");//移除禁用状态改为可用
                                msg_mobile.innerHTML = "<font color = 'green'>√</font>";
                            }else if(xhr.responseText==2){
                                document.getElementById("register").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_mobile.innerHTML = "<font color = 'red'>输入手机号格式不正确！</font>";
                            }
                        }
                    }
                }
                //创建连接
                xhr.open("post","${pageContext.request.contextPath}/check/ckmobile",true);
                xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                //发送请求
                xhr.send("mobile="+mobile);
            }
        }
    </script>
</head>

<body class="main">

<jsp:include page="head.jsp" />

<form method="post" action="/admin/register">
    <table width="60%" border="0" cellspacing="2" class="upline" style="margin: auto">
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="text-align: center; width: 10%">网上书城后台管理系统</td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="text-align: center; width: 10%" height="30px"></td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style=" text-align: center; width: 10%" height="30px">管理员注册</td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">昵称：</td>
            <td style="width: 10%">
                <input id="nickname" type="text" name="adminname" value=""/>
            </td>
            <td><span id="msg_nickname">昵称长度至少为4位!</span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">手机号：</td>
            <td style="width: 10%">
                <input id="mobile" type="text" name="mobile" value=""/>
            </td>
            <td><span id="msg_mobile"></span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">邮箱：</td>
            <td style="width: 10%">
                <input id="email" type="text" name="email" value=""/>
            </td>
            <td><span id="msg_email"></span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">密码：</td>
            <td style="width: 10%">
                <input id="pwd" type="password" name="password"/>
            </td>
            <td><span id="msg_pwd">密码长度需大于6位！${pwd_msg}</span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">确认密码：</td>
            <td style="width: 10%">
                <input id="repwd" type="password" name="repassword"/>
            </td>
            <td><span id="msg_repwd">再次输入您的密码！</span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">性别：</td>
            <td style="width: 10%">&nbsp;&nbsp;
                <input type="radio" name="gender" value="男" checked="checked" /> 男
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="gender" value="女" /> 女
            </td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">验证图片：</td>
            <td style="width: 10%">
                <img id="img" src="/check/code" width="180" height="30" style="height: 30px"/>
            </td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="width: 15%">
                <a href="javascript:void(0);" onclick="changeImage()">看不清，换一张！</a>
            </td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">验证码：</td>
            <td style="width: 10%">
                <input id="imgcode" name="imgcode" type="text" value=""/>
            </td>
            <td><span id="msg_imgcode"></span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="text-align: center; width: 10%">
                <button id="register" type="submit" value="" style="height: 30px;text-align: left">注册</button>
                <button type="reset" value="" style="height: 30px;text-align: right">重置</button>
            </td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="text-align: center; width: 10%" height="40px"></td>
            <td style="text-align: left"></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">
                <a href="login.jsp">会员登录</a>
            </td>
            <td style="text-align: center; width: 10%">
                <a href="adminlogin.jsp">已有管理员账号？直接登录</a>
            </td>
            <td style="text-align: left">
                <a href="register.jsp">会员注册</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="foot.jsp" />

</body>
</html>
