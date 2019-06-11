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
        /*var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数

        // 发送短信验证码
        function sendMessage() {
            curCount = count;
            // 设置button效果，开始计时
            document.getElementById("btnSendCode").setAttribute("disabled","true" );//设置按钮为禁用状态
            document.getElementById("btnSendCode").value="请在" + curCount + "后再次获取";//更改按钮文字
            InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
            // 向后台发送处理数据
            var sendMsg_xhr = new XMLHttpRequest();
            sendMsg_xhr.onreadystatechange = function(){
                if(sendMsg_xhr.readyState==4) {//请求一切正常
                    if (sendMsg_xhr.status == 200) {//服务器响应一切正常
                        var msg_msgcode = document.getElementById("msg_msgcode");
                        msg_msgcode.innerHTML = "短信验证码为"+"<font color='green'>"+sendMsg_xhr.responseText+"<font>";
                    }
                }
            }

            // flag = 1 表示后台发送验证码
            sendMsg_xhr.open("get","/user/checkmsgcode?flag="+1,true);
            sendMsg_xhr.send(null);
        }

        //timer处理函数
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);// 停止计时器
                document.getElementById("btnSendCode").removeAttribute("disabled");//移除禁用状态改为可用
                document.getElementById("btnSendCode").value="重新发送验证码";
            }else {
                curCount--;
                document.getElementById("btnSendCode").value="请在" + curCount + "秒后再次获取";
            }
        }*/

        window.onload=function(){

            // 获取手机号的标签
            var mobileElement = document.getElementById("mobile");

            // 获取密码
            var pwdElement = document.getElementById("pwd");

            // 获取图片验证码
            var imgcodeElement = document.getElementById("imgcode");

            // 验证图片验证码
            imgcodeElement.onblur = function(){
                var imgcode = this.value;

                var msg_imgcode = document.getElementById("msg_imgcode");
                if (imgcode == null || imgcode == ''){
                    document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_imgcode.innerHTML = "<font color='red'>验证码不能为空！</font>";
                    return;
                }

                var img_xhr = new XMLHttpRequest();
                img_xhr.onreadystatechange = function () {
                    if(img_xhr.readyState==4) {//请求一切正常
                        if (img_xhr.status == 200) {//服务器响应一切正常

                            if (img_xhr.responseText==1){
                                document.getElementById("login").removeAttribute("disabled");//移除禁用状态改为可用
                                msg_imgcode.innerHTML = "<font color='green'>√</font>";
                            } else if (img_xhr.responseText==0){
                                document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_imgcode.innerHTML = "<font color='red'>×</font>";
                            }
                        }
                    }
                }
                img_xhr.open("post","${pageContext.request.contextPath}/check/ckcode",true);
                img_xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                img_xhr.send("ckcode="+imgcode);
            }

            // 验证密码
            pwdElement.onblur = function() {
                var pwd = this.value;

                var msg_pwd = document.getElementById("msg_pwd");
                if(pwd.length<6){
                    document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_pwd.innerHTML = "<font color='red'>密码长度不足6位！</font>";
                }else {
                    document.getElementById("login").removeAttribute("disabled");//移除禁用状态改为可用
                    msg_pwd.innerHTML = "<font color='green'>√</font>";
                }
            }

            // 判断手机号是否可以使用
            mobileElement.onblur = function(){
                var mobile = this.value;//this等价于nameElement

                var msg_mobile = document.getElementById("msg_mobile");
                if (mobile == null || mobile == ''){
                    document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_mobile.innerHTML = "<font color = 'red'>手机号不能为空！</font>";
                    return;
                } else if (!(/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3|7])|(18([0-3]|[5-9])))\d{8}$/.test(mobile))) {
                    document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                    msg_mobile.innerHTML = "<font color = 'red'>手机号格式不正确！</font>";
                }

                //创建XMLHttpRequest对象
                var xhr = new XMLHttpRequest();
                //处理结果
                xhr.onreadystatechange = function(){
                    if(xhr.readyState==4){//请求一切正常
                        if(xhr.status==200){//服务器响应一切正常

                            if(xhr.responseText==1){
                                document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_mobile.innerHTML =  "<font color='red'>该手机号还未注册！</font>";
                            }else if(xhr.responseText==0){
                                document.getElementById("login").removeAttribute("disabled");//移除禁用状态改为可用
                                msg_mobile.innerHTML = "<font color = 'green'>√</font>";
                            }else if(xhr.responseText==2){
                                document.getElementById("login").setAttribute("disabled","true" );//设置按钮为禁用状态
                                msg_mobile.innerHTML = "<font color = 'red'>查询手机号异常！</font>";
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

<form method="post" action="/admin/login">
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
            <td style=" text-align: center; width: 10%" height="30px">管理员登录</td>
            <td></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">手机号：</td>
            <td style="width: 10%">
                <input id="mobile" type="text" name="mobile" value=""/>
            </td>
            <td><span id="msg_mobile">${mobile_msg}</span></td>
        </tr>
        <tr>
            <td style="text-align: right"; width="40%">密码：</td>
            <td style="width: 10%">
                <input id="pwd" type="password" name="password"/>
            </td>
            <td><span id="msg_pwd">${pwd_msg}</span> </td>
        </tr>

        <tr>
            <td style="text-align: right"; width="40%">验证图片：</td>
            <td style="width: 10%">
                <img id="img" src="/check/code" width="180" height="30" style="height: 30px">
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
                <input id="imgcode" name="ckcode" type="text" value=""/>
            </td>
            <td><span id="msg_imgcode">${ckcode_msg}</span></td>
        </tr>

        <tr>
            <td style="text-align: right"; width="40%"></td>
            <td style="text-align: center; width: 10%">
                <button id="login" type="submit" value="" style="height: 30px;text-align: left">登录</button>
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
                <a href="adminregister.jsp">没有管理员账号？前往注册</a>
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
