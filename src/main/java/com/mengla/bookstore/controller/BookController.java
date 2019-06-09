package com.mengla.bookstore.controller;

import com.mengla.bookstore.model.Book;
import com.mengla.bookstore.model.Condition;
import com.mengla.bookstore.model.PageBooks;
import com.mengla.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// /book/booksearch
// /book/booksearchbylikename

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * 根据书籍名称查询
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/searchbooksbyname",method = RequestMethod.GET)
    public void searchBooksByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String bookName = request.getParameter("name");

        if(bookName == null || "".equals(bookName)){
            response.sendRedirect("/home.jsp");
        }else{
            List<Book> books = bookService.searchBooksByLikeName(bookName);
            if(books==null){
                out.println("对不起！没有找到该书籍的相关信息！！我们会尽快更新书籍库！！！");
                out.println("2s后返回主页！");
                response.setHeader("refresh","2;url=/home.jsp");
            }else {
                request.getSession().setAttribute("books",books);
                response.sendRedirect("/product_list.jsp");
            }
        }*/

        String bookName = request.getParameter("name");

        int pageSize = 5;
        int currentPage = 1;//当前页
        int bookSize;
        String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据

            if(currPage!=null){//第一次访问资源时，currPage可能是null
                currentPage = Integer.parseInt(currPage);
            }
            bookSize = bookService.searchBooksByLikeName(bookName).size();


        //分页查询，并返回PageBean对象
        PageBooks pageBooks = bookService.searchBooksByPage(currentPage,pageSize,"",bookName);

        HttpSession session = request.getSession();
        session.setAttribute("name",bookName);
        session.setAttribute("booksize",bookSize);
        session.setAttribute("category","搜索结果");
        session.setAttribute("pagebooks", pageBooks);
        response.sendRedirect("/result_list.jsp");
    }

    /**
     * 条件查询
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/findbookbycondition",method = RequestMethod.POST)
    public void findBookByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Condition condition = new Condition();

        String bookid = request.getParameter("bookid");
        String bookname = request.getParameter("name");
        String category = request.getParameter("category");
        String minPrice = request.getParameter("minprice");
        String maxPrice = request.getParameter("maxprice");

        if(bookid!=null && !"".equals(bookid)){
            condition.setBookId(Long.parseLong(bookid));
        }

        if(bookname==null || "".equals(bookname)){
            condition.setBookName("%");
        }else {
            condition.setBookName(bookname);
        }

        if(category==null || "".equals(category)){
            condition.setCategory("%");
        }else {
            condition.setCategory(category);
        }

        if (minPrice==null || "".equals(minPrice)){
            condition.setMinPrice(0);
        }else {
            condition.setMinPrice(Double.parseDouble(minPrice));
        }

        if(maxPrice==null || "".equals(maxPrice)){
            condition.setMaxPrice(Integer.MAX_VALUE*1.0);
        }else {
            condition.setMaxPrice(Double.parseDouble(maxPrice));
        }

        List<Book> books =  bookService.findBookByCondition(condition);
        String min = null;
        String max = null;

        if("%".equals(bookname)){
            condition.setBookName("");
        }
        if("%".equals(category)){
            condition.setCategory("");
        }
        if("".equals(minPrice) || minPrice == null){
            min = "";
        }
        if("".equals(maxPrice) || maxPrice == null){
            max = "";
            condition.setMaxPrice(0);
        }
        request.getSession().setAttribute("books",books);
        request.getSession().setAttribute("nim",min);
        request.getSession().setAttribute("max",max);
        request.getSession().setAttribute("condition",condition);
        response.sendRedirect("/admin/products/list.jsp");
    }

    /**
     * 根据id查找书籍
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/findbook",method = RequestMethod.GET)
    public void findBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();

        String bookId = request.getParameter("bookid");

        if (bookId != null) {
            Book book = bookService.searchBookById(Long.parseLong(bookId));
            request.getSession().setAttribute("book",book);
            response.sendRedirect("/product_info.jsp");
        }else {
            request.getSession().setAttribute("book",null);
            response.sendRedirect("/product_info.jsp");
        }
    }

    /**
     * 根据id获取待修改书籍，将书籍对象放入Session中
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId = request.getParameter("bookid");

        Book book = bookService.searchBookById(Long.parseLong(bookId));
        request.getSession().setAttribute("book",book);

        response.sendRedirect("/admin/products/edit.jsp");
    }

    /**
     * 修改书籍信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/updatebook",method = RequestMethod.POST)
    public void updatebook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Book book = (Book) request.getSession().getAttribute("book");

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String pnum = request.getParameter("pnum");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        if("".equals(name) || "".equals(price) || "".equals(pnum) || "".equals(category)){
            out.println("商品名称、商品价格、商品数量、商品类别均不能为空！");
            out.println("2s后返回！");
            response.setHeader("refresh","2;url=/admin/products/edit.jsp");
            return;
        }

        book.setBookName(name);
        book.setPrice(Double.parseDouble(price));
        book.setPnum(Integer.parseInt(pnum));
        book.setCategory(category);

        if (description!=null){
            book.setDescription(description);
        }

        if(bookService.updateBook(book)>0){
            response.sendRedirect("/book/booksearch");
            return;
        }else {
            out.println("修改失败！");
            out.println("2s后返回！");
            response.setHeader("refresh","2;url=/admin/products/list.jsp");
        }
    }

    /**
     * 添加书籍
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/addbook",method = RequestMethod.POST)
    public void addbook(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String pnum = request.getParameter("pnum");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        Book book = new Book();

        if (name==null || price==null || pnum == null || category == null){
            out.println("商品名称、商品价格、商品数量、商品类别均不能为空！");
            out.println("2s后返回！");
            response.setHeader("refresh","2;url=/admin/products/add.jsp");
        }else {
            book.setBookName(name);
            book.setPrice(Integer.parseInt(price));
            book.setPnum(Integer.parseInt(pnum));
            book.setCategory(category);
            book.setDescription(description);

            bookService.insertBook(book);

            response.sendRedirect("/book/booksearch");
        }
    }

    /**
     * 查询所有书籍
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/booksearch",method = RequestMethod.GET)
    public void bookSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = bookService.searchBooks();

        request.getSession().setAttribute("books",books);
        response.sendRedirect("/admin/products/list.jsp");
    }

    /**
     * 购物车功能
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/addcart",method = RequestMethod.GET)
    public void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String bookid = request.getParameter("bookid");
        String number = request.getParameter("num");

        Map<Book,String> cart = (Map<Book,String>)request.getSession().getAttribute("cart");
        Book book = bookService.searchBookById(Long.parseLong(bookid));

        if (number!=null){
            if("0".equals(number)){
                cart.remove(book);
                request.getSession().setAttribute("cart",cart);
                response.sendRedirect("/cart.jsp");
                return;
            }else {
                cart.put(book,number);
                request.getSession().setAttribute("cart",cart);
                response.sendRedirect("/cart.jsp");
                return;
            }
        }

        int num = 1;

        if (cart==null){
            cart = new HashMap<>();
        }

        if (cart.containsKey(book)){
            num = Integer.parseInt(cart.get(book))+1;
        }

        cart.put(book,num+"");

        request.getSession().setAttribute("cart",cart);
        response.sendRedirect("/cart.jsp");
    }

    /**
     * 完成按门类检索
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public void category(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int pageSize = 5;

        int currentPage = 1;//当前页

        String currPage = request.getParameter("currentPage");//从上一页或下一页得到的数据
        if(currPage!=null){//第一次访问资源时，currPage可能是null
            currentPage = Integer.parseInt(currPage);
        }
        String category = request.getParameter("category");

        int booksize;

        String cate = "";
        if(category==null||"所有门类".equals(category)){
            cate ="%";
            category = "所有门类";
            booksize = bookService.searchBooksByCategory(cate).size();
        }else {
            cate = category;
            booksize = bookService.searchBooksByCategory(cate).size();
        }

        //分页查询，并返回PageBean对象
        PageBooks pageBooks = bookService.searchBooksByPage(currentPage,pageSize,cate,"");

        request.getSession().setAttribute("booksize",booksize);
        request.getSession().setAttribute("category",category);
        request.getSession().setAttribute("pagebooks", pageBooks);
        response.sendRedirect("/product_list.jsp");
    }
}
