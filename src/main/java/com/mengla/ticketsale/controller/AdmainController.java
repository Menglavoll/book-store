package com.mengla.ticketsale.controller;

import com.mengla.ticketsale.model.User;
import com.mengla.ticketsale.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping(value = "/manger")
public class AdmainController {

    @Autowired
    private UserService userService;

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = userService.searchUsersByLikeName(username);

        PrintWriter out = response.getWriter();

        if(!users.isEmpty()){
            for (User u :users){
                if(u.getUserName().equals(username) && u.getPassword().equals(password) && u.getRoleId()==10){
                    out.println("登陆成功！2s后返回管理员主页！");
                    request.getSession().setAttribute("u",u);
                    response.setHeader("refresh","2;url="+request.getContextPath()+"/admin/login/home.jsp");
//                    request.getRequestDispatcher("/home.jsp").forward(request,response);
                }else {
                    request.setAttribute("pwd_msg","密码不正确！");
                    request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
                    return;
                }
            }
        }else {
            request.setAttribute("pwd_msg","用户名不存在！");
//            response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/adminlogin.jsp").forward(request,response);
            return;
        }
    }
}
