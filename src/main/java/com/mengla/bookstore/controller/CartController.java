package com.mengla.bookstore.controller;

import com.mengla.bookstore.model.Book;
import com.mengla.bookstore.model.Cart;
import com.mengla.bookstore.model.User;
import com.mengla.bookstore.service.IBookService;
import com.mengla.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IBookService bookService;

    /**
     * 查找购物车
     * @param request
     * @param response
     */
    @RequestMapping(value = "/searchcarts",method = RequestMethod.GET)
    public void searchCarts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user = (User)request.getSession().getAttribute("user");
        List<Cart> carts = cartService.findCartsByUserId(user.getUserId());
        Map<Cart,String> cartMap = null;

        if (carts == null){
            request.getSession().setAttribute("cart",cartMap);
            response.sendRedirect("/cart.jsp");
        }else {
            cartMap = new  HashMap<Cart,String>();
            for (int i = 0;i<carts.size();i++){
                cartMap.put(carts.get(i),carts.get(i).getNum()+"");
            }
            request.getSession().setAttribute("cart",cartMap);
            response.sendRedirect("/cart.jsp");
            return;
        }
    }

    /**
     * 添加购物车
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public void addCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String bookId = request.getParameter("bookid");
        String number = request.getParameter("num");

        Map<Cart,String> carts = (Map<Cart,String>)request.getSession().getAttribute("cart");
        User user = (User)request.getSession().getAttribute("user");
        Cart cart = cartService.findCart(user.getUserId(),Long.parseLong(bookId));

        if (number != null && carts != null){
            if("0".equals(number)){
                carts.remove(cart);
                cartService.deleteCart(user.getUserId(),Long.parseLong(bookId));
                response.sendRedirect("/cart/searchcarts");
                return;
            }else {
                cart.setNum(Integer.parseInt(number));
                double count = Integer.parseInt(number) * cart.getPrice();
                cart.setCount(count);
                cartService.updateCart(cart);
                carts.put(cart,number);
                response.sendRedirect("/cart/searchcarts");
                return;
            }
        }

        // 新建购物车Map对象
        if (carts==null){
            carts = new HashMap<>();
        }

        if (cart == null){
            cart = new Cart();

            Book book = bookService.searchBookById(Long.parseLong(bookId));
            cart.setUserId(user.getUserId());
            cart.setBookId(Long.parseLong(bookId));
            cart.setBookName(book.getBookName());
            cart.setPrice(book.getPrice());
            cart.setPnum(book.getPnum());
            cart.setNum(1);
            cart.setCount(1 * book.getPrice());
            cartService.insertCart(cart);

            response.sendRedirect("/cart/searchcarts");
            return;
        }

        int num =  cart.getNum()+1;
        double count = cart.getPrice();
        // 更新购物车Map中数量
        carts.remove(cart);
        cart.setNum(num);
        cart.setCount(num * count);
        carts.put(cart,cart.getNum()+"");
        cartService.updateCart(cart);
        response.sendRedirect("/cart/searchcarts");
    }
}
