package com.mengla.bookstore.controller;

import com.mengla.bookstore.common.MD5Util;
import com.mengla.bookstore.model.Admin;
import com.mengla.bookstore.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@RestController
@RequestMapping(value = "/admin")
public class AdmainController {

    @Autowired
    private IAdminService adminService;

    /**
     * 管理员返回主页
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "return",method = RequestMethod.GET)
    public void returnHome(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        out.println("2s after return to home page");
        response.setHeader("refresh","2;url=/home.jsp");
    }

    /**
     * 管理员注销
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/unlogin",method = RequestMethod.GET)
    public void adminUnlogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().removeAttribute("admin");
        response.sendRedirect("/home.jsp");
    }

    /**
     * 管理员登录
    * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void adminLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");

        if(!ckcode.equals(checkcode_session)){
            out.println("The check code is error! 2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminlogin.jsp");
            return;
        }

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        Admin admin = adminService.findAdminByMobile(mobile);

        if(admin != null){
            if(admin.getPassword().equals(MD5Util.md5Encode(password,"UTF-8"))){
                request.getSession().setAttribute("admin",admin);
                response.sendRedirect("/admin/login/home.jsp");
            }else {
                out.println("The password is error!2s after return!");
                response.setHeader("refresh","200;url="+request.getContextPath()+"/adminlogin.jsp");
                return;
            }
        }else {
            out.println("The mobile is error!2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminlogin.jsp");
            return;
        }
    }

    /**
     * 管理员注册
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void adminRegister(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 验证图片验证码
        String imgcode = request.getParameter("imgcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");
        if (!imgcode.equals(checkcode_session)){
            out.println("The check code is error! 2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
            return;
        }

        // 验证密码
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        if (password == null || password.equals("") || repassword == null || repassword.equals("") || !password.equals(repassword)){
            out.println("The password is error!2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
            return;
        }

        // 验证手机号、邮箱和昵称
        String mobile = request.getParameter("mobile");
        if( mobile == null || mobile.equals("") ){
            out.println("The mobile is error!2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
            return;
        }
        String email = request.getParameter("email");
        if(email == null || email.equals("")){
            out.println("The emsil is error!2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
            return;
        }
        String adminname = request.getParameter("adminname");
        if( adminname == null || adminname.equals("") ){
            out.println("The nickname is error!2s after return!");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
            return;
        }

        // 创建admin对象
        Admin admin = new Admin();
        admin.setAdminName(adminname);
        admin.setMobile(mobile);
        admin.setEmail(email);
        admin.setPassword(MD5Util.md5Encode(password,"UTF-8"));
        admin.setGender(request.getParameter("gender"));
        admin.setEncrypType(1);
        admin.setRoleId(1);
        admin.setRegisterTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setIsDeleted(0);
        admin.setIsEnabled(1);

        if (adminService.registerAdmin(admin) > 0){
            out.println("successful！注册成功！2s后返回管理员登录界面！");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminlogin.jsp");
        }else {
            out.println("Error！注册异常失败！2s后返回！");
            response.setHeader("refresh","2;url="+request.getContextPath()+"/adminregister.jsp");
        }
    }
}
