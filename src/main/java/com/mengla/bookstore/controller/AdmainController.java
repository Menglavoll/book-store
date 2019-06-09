package com.mengla.bookstore.controller;

import com.mengla.bookstore.common.MD5Util;
import com.mengla.bookstore.model.Admin;
import com.mengla.bookstore.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping(value = "/manger")
public class AdmainController {

    @Autowired
    private IAdminService adminService;

    /**
     * 管理员登录
    * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void mangerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");

        if(!checkcode_session.equals(ckcode)){
            request.setAttribute("ckcode_msg","验证码错误！");
            request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
            return;
        }

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        Admin admin = adminService.findAdminByMobile(mobile);

        PrintWriter out = response.getWriter();

        if(admin != null){
            if(admin.getPassword().equals(MD5Util.md5Encode(password,"UTF-8"))){
                out.println("登陆成功！2s后返回管理员主页！");
                request.getSession().setAttribute("admin",admin);
                response.setHeader("refresh","2;url="+request.getContextPath()+"/admin/login/home.jsp");
//                request.getRequestDispatcher("/home.jsp").forward(request,response);
            }else {
                request.setAttribute("pwd_msg","密码不正确！");
                request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
                return;
            }
        }else {
            request.setAttribute("pwd_msg","该手机号尚未注册，请前往注册页面！");
//            response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
            return;
        }
    }
}
