package com.mengla.bookstore.controller;

import com.mengla.bookstore.common.MD5Util;
import com.mengla.bookstore.model.User;
import com.mengla.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户信息修改
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public void updateuser(HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 验证图片验证码
        String ckcode = request.getParameter("ckcode");
        String ckcode_session = (String)request.getSession().getAttribute("checkcode_session");
        if (ckcode == null || ckcode == "" || ckcode.equals(ckcode_session)){
            out.println("验证码输入有误！2s后返回！！");
            response.setHeader("refresh","2;url=/modifyuserinfo.jsp");
            return;
        }

        // 验证昵称是否为空
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        if(username==null){
            out.println("昵称不能少于4位！2s后返回！！");
            response.setHeader("refresh","2;url=/modifyuserinfo.jsp");
            return;
        }

        // 验证密码
        String password = request.getParameter("password");
        String repwd = request.getParameter("repassword");
        // 验证密码是否为空
        if( password == null || password == "" || repwd == null || repwd == ""){
            out.println("密码不能少于6位！2s后返回！！");
            response.setHeader("refresh","2;url=/modifyuserinfo.jsp");
            return;
        }
        // 验证两次输入密码是否一致
        if( password != null && !password.equals(repwd)){
            out.println("两次输入密码不一致！2s后返回！！");
            response.setHeader("refresh","2;url=/modifyuserinfo.jsp");
            return;
        }

        String gender = request.getParameter("gender");

        user.setUserName(username);
        user.setPassword(password);
        user.setGender(gender);

        // 更新数据
        if(userService.update(user) > 0){
            out.println("修改成功！2s后返回主页！");
            request.getSession().setAttribute("u",user);
            response.setHeader("refresh","2;url="+request.getContextPath()+"/home.jsp");
        }else {
            out.println("修改失败！2s后返回修改页面！");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/modifyuserinfo.jsp");
        }
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // 验证图片验证码
        String ckcode = request.getParameter("ckcode");
        String session_ckcode = (String)request.getSession().getAttribute("checkcode_session");

        if(ckcode == null || ckcode == "" || !ckcode.equals(session_ckcode)){
            out.println("验证码输入有误！2s后返回登录！");
            response.setHeader("refresh","2;url=/login.jsp");
            return;
        }

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = userService.searchUserByMobile(mobile);

        if(user != null){
            if(user.getMobile().equals(mobile) &&
                    user.getPassword().equals(MD5Util.md5Encode(password,"UTF-8"))){
                out.println("登陆成功！2s后返回主页！");
                request.getSession().setAttribute("user",user);
                response.setHeader("refresh","2;url="+request.getContextPath()+"/home.jsp");
            }else {
                request.setAttribute("pwd_msg","手机号或密码不正确！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("pwd_msg","改手机号尚未注册！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/unlogin",method = RequestMethod.GET)
    public void unlogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("注销成功！2s后跳转到主页！");

        request.getSession().invalidate();
        response.setHeader("refresh","2;url="+request.getContextPath()+"/home.jsp");
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String repwd = request.getParameter("repassword");

        if(!password.equals(repwd)&&password!=null){
            request.setAttribute("pwd_msg","两次输入的密码不一致！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        String mobile = request.getParameter("mobile");

        if (userService.searchUserByMobile(mobile) != null){
            request.setAttribute("msg_register","对不起！该手机号已被注册，请直接登陆！！！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String introduce = request.getParameter("introduce");

        User user = new User();
        user.setUserName(username);
        user.setPassword(MD5Util.md5Encode(password,"UTF-8"));
        user.setGender(gender);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setIntroduce(introduce);
        user.setActiveCode(UUID.randomUUID().toString());
        user.setRoleId(11);
        user.setRegisterTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDeleted(0);
        user.setIsEnabled(1);
        user.setEncrypType(1);// MD5加密

        if(userService.insertUser(user) > 0){
            response.sendRedirect("/registersuccess.jsp");
        }else {
            request.setAttribute("msg_register","对不起！注册异常失败，请重试！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
}
