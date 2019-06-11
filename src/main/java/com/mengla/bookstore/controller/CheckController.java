package com.mengla.bookstore.controller;

import com.mengla.bookstore.model.User;
import com.mengla.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping(value = "/check")
public class CheckController {

    @Autowired
    private IUserService userService;

    /**
     * 验证手机号
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/ckmobile",method = RequestMethod.POST)
    public void checkMobile(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String mobile = request.getParameter("mobile");
        if (mobile != null){
            User user = userService.searchUserByMobile(mobile);
            if (user != null){
                out.println(0);// 该手机号已存在
            }else {
                out.println(1);// 可以使用
            }
        }else {
            out.println(2);// 异常错误
        }
    }

    /**
     * 验证图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/ckcode",method = RequestMethod.POST)
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String)request.getSession().getAttribute("checkcode_session");

        if(ckcode != null && checkcode_session != null){
            if(ckcode.equals(checkcode_session)){
                out.println(1);
            }else {
                out.println(0);
            }
        }else {
            out.println(0);
        }
    }
}
