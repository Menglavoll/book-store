package com.mengla.bookstore.controller;

import com.mengla.bookstore.model.Cart;
import com.mengla.bookstore.model.Order;
import com.mengla.bookstore.model.PackageList;
import com.mengla.bookstore.model.User;
import com.mengla.bookstore.service.ICartService;
import com.mengla.bookstore.service.IOrderService;
import com.mengla.bookstore.service.IPackageService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IPackageService packageService;

    /**
     * 创建订单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String receiver = request.getParameter("receiver");
        if (receiver == null || receiver.equals("")){
            out.println("The receiver can't null,2s after return!");
            response.setHeader("refresh","2;url=/order.jsp");
            return;
        }

        String mobile = request.getParameter("mobile");
        if (mobile == null || mobile.equals("")){
            out.println("The mobile can't null,2s after return!");
            response.setHeader("refresh","2;url=/order.jsp");
            return;
        }

        String address = request.getParameter("address");
        if (address == null || address.equals("")){
            out.println("The address can't null,2s after return!");
            response.setHeader("refresh","2;url=/order.jsp");
            return;
        }

        User user = (User)request.getSession().getAttribute("user");
        List<Cart> carts = cartService.findCartsByUserId(user.getUserId());

        if (carts!=null || !carts.isEmpty()){
            double total = 0;
            Date date = new Date();
            int orderPackage = Objects.hash(date);
            List<PackageList> packageLists = new ArrayList<>();

            for (int i = 0;i<carts.size();i++){
                Cart cart = carts.get(i);
                total += cart.getCount();
                PackageList packageList = new PackageList();

                packageList.setOrderPackage(orderPackage);
                packageList.setBookId(cart.getBookId());
                packageList.setBookName(cart.getBookName());
                packageList.setNum(cart.getNum());
                packageList.setCount(cart.getCount());
                packageList.setPrice(cart.getPrice());
                packageLists.add(packageList);
            }

            Order order = new Order();
            order.setOrderPackage(orderPackage);
            order.setUserId(user.getUserId());
            order.setTotal(total);
            order.setReceiver(receiver);
            order.setRecMobile(mobile);
            order.setAddress(address);
            order.setCreateTime(date);
            order.setState(0);

            if (orderService.insertOrderAndPackage(order,packageLists) > 0){
                cartService.deleteCarts(carts);
                response.sendRedirect("/createOrderSuccess.jsp");
            }

        }else {
            out.println("An unknown error occurred! 2s after return!");
            response.setHeader("refresh","2;url=/order.jsp");
        }

    }

    /**
     * 查看所有订单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public void findAllOrders(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        List<Order> adminorders = orderService.findOrders();

        request.getSession().setAttribute("adminorders",adminorders);
        response.sendRedirect("/admin/orders/list.jsp");
    }

    /**
     * 查看订单
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public void findOrders(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user = (User)request.getSession().getAttribute("user");

        List<Order> orders = orderService.findOrdersByUserId(user.getUserId());

        request.getSession().setAttribute("ordersize",orders.size());
        request.getSession().setAttribute("orders",orders);
        response.sendRedirect("/orderlist.jsp");
    }

    /**
     * 订单详细信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/orderinfo",method = RequestMethod.GET)
    public void orderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String orderPackage = request.getParameter("orderPackage");

        List<PackageList> packageLists =
                packageService.findPackageByOrder(Integer.parseInt(orderPackage));

        Order order = orderService.findOrderByOrder(Integer.parseInt(orderPackage));
/*
        PrintWriter out = response.getWriter();

        out.println(packageLists);
        response.setHeader("rehresh","200;url=/orderInfo.jsp");*/

        request.getSession().setAttribute("packages",packageLists);
        request.getSession().setAttribute("order",order);
        response.sendRedirect("/orderInfo.jsp");
    }
}
