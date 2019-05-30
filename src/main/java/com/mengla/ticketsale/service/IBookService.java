package com.mengla.ticketsale.service;

import com.mengla.ticketsale.model.Book;
import com.mengla.ticketsale.model.Condition;
import com.mengla.ticketsale.model.PageBooks;

import java.util.List;

public interface IBookService {
    /**
     * 查询所有书籍
     * @return
     */
    public List<Book> searchBooks();

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public List<Book> searchBooksByLikeName(String name);

    /**
     * 通过id查询
     * @param bookId
     * @return
     */
    public Book searchBookById(String bookId);

    /**
     * 根据类别查询
     * @param category
     * @return
     */
    public List<Book> searchBooksByCategory(String category);

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页中有几条数据
     * @param category 类别
     * @return
     */
    public PageBooks searchBooksByPage(int currentPage, int pageSize,String category,String bookName);

    /**
     * 插入书籍
     * @param book
     * @return
     */
    public int insertBook(Book book);

    /**
     * 跟新书籍信息
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 条件查询
     * @param condition
     * @return
     */
    public List<Book> findBookByCondition(Condition condition);
}
