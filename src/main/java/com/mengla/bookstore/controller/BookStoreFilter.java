package com.mengla.bookstore.controller;

import com.mengla.bookstore.model.Admin;
import com.mengla.bookstore.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "modifyuserinfo",urlPatterns = "/*")
public class BookStoreFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String addr = req.getRequestURI();
        User user = (User)req.getSession().getAttribute("user");
        Admin admin = (Admin)req.getSession().getAttribute("admin");

        if( (addr.contains("modifyuserinfo") || addr.contains("cart")
        || addr.contains("order") || addr.contains("MyAccount")
                || addr.contains("pay") ) && user == null){

            if ( ( addr.contains("order/findall") || addr.contains("admin/orders/list") ) && admin != null){
                chain.doFilter(request,response);
            }else {

                resp.sendRedirect("/login.jsp");
            }

        } else if ( (addr.contains("admin/login/") || addr.contains("admin/orders/")
                || addr.contains("admin/products/") ) && admin == null){
            resp.sendRedirect("/adminlogin.jsp");
        }else {
            chain.doFilter(request,response);
        }
    }
}
