package com.mengla.ticketsale.controller;

import com.mengla.ticketsale.model.User;
import com.mengla.ticketsale.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// user/searchusers
// user/searchusersbylikename

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查找全部用户
     * @return
     */
    @RequestMapping(value = "/searchusers",method = RequestMethod.GET)
    public List<User> searchUsers() {
        return userService.searchUsers();
    }

    /**
     * 根据Id查找用户
     * @param userid
     * @return
     */
    public User searchUserById(int userid){
        return userService.searchUserById(userid);
    }

    /**
     * 根据姓名查找用户
     * @param username
     * @return
     */
    @RequestMapping(value = "/searchusersbylikename",method = RequestMethod.GET)
    public List<User> SearchUsersByLikeName(String username) {
        return userService.searchUsersByLikeName(username);
    }

    /**
     * 添加用户
     * @param user
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public void insert(User user){
        userService.insertUser(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public int update(User user){
        return userService.update(user);
    }

    /**
     * 用户信息修改
     * @param request
     * @param response
     */
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public void updateuser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        User user = (User) request.getSession().getAttribute("u");

        String username = request.getParameter("username");

        if(username==null && !SearchUsersByLikeName(username).isEmpty()){
            request.setAttribute("name_msg","该用户名已存在！");
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
            return;
        }

        String password = request.getParameter("password");
        String repwd = request.getParameter("repassword");

        if( password!=null && !password.equals(repwd)){
            request.setAttribute("pwd_msg","两次输入的密码不一致！");
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
            return;
        }

        String gender = request.getParameter("gender");
        String telephone = request.getParameter("telephone");

        user.setUserName(username);
        user.setPassword(password);
        user.setGender(gender);

        if (telephone!=null) {
            user.setTelephone(telephone);
        }

        PrintWriter out = response.getWriter();

        if(update(user)>0){
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
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");

        if(!checkcode_session.equals(ckcode)){
            request.setAttribute("ckcode_msg","验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = userService.searchUsersByLikeName(username);

        PrintWriter out = response.getWriter();

        if(!users.isEmpty()){
            for (User u :users){
                if(u.getUserName().equals(username) && u.getPassword().equals(password)){
                    out.println("登陆成功！2s后返回主页！");
                    request.getSession().setAttribute("u",u);
                    response.setHeader("refresh","2;url="+request.getContextPath()+"/home.jsp");
//                    request.getRequestDispatcher("/home.jsp").forward(request,response);
                }else {
                    request.setAttribute("pwd_msg","密码不正确！");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                    return;
                }
            }
        }else {
            request.setAttribute("pwd_msg","用户名不存在！");
//            response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/unlogin",method = RequestMethod.GET)
    public void unlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");

        String password = request.getParameter("password");
        String repwd = request.getParameter("repassword");

        if(!checkcode_session.equals(ckcode)){
            request.setAttribute("ckcode_msg","验证码错误！");
//            response.sendRedirect("/register.jsp");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        if(!password.equals(repwd)&&password!=null){
            request.setAttribute("pwd_msg","两次输入的密码不一致！");
//            response.sendRedirect("/register.jsp");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        String username = request.getParameter("username");

        if (!SearchUsersByLikeName(username).isEmpty()){
            request.setAttribute("name_msg","对不起！该用户已存在！！");
//            response.sendRedirect("/register.jsp");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String telephone = request.getParameter("telephone");
        String introduce = request.getParameter("introduce");

        User user = new User();

        user.setUserName(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setIntroduce(introduce);
        user.setActiveCode(UUID.randomUUID().toString());
        user.setRoleId(2);
        user.setState(2);
        user.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")
                .format(new Date()));

        insert(user);

        response.sendRedirect("/registersuccess.jsp");
//        request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);

    }
}
