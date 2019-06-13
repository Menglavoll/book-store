package com.mengla.bookstore.service;

import com.mengla.bookstore.model.Book;
import com.mengla.bookstore.model.Condition;
import com.mengla.bookstore.model.PageBooks;

import java.util.List;

public interface IBookService {
    /**
     * 查询所有书籍
     * @return
     */
    List<Book> searchBooks();

    /**
     * 根据名称查询
     * @param bookName
     * @return
     */
    List<Book> searchBooksByLikeName(String bookName);

    /**
     * 通过id查询
     * @param bookId
     * @return
     */
    Book searchBookById(long bookId);

    /**
     * 根据类别查询
     * @param category
     * @return
     */
    List<Book> searchBooksByCategory(String category);

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页中有几条数据
     * @param category 类别
     * @return
     */
    PageBooks searchBooksByPage(int currentPage, int pageSize, String category, String bookName);

    /**
     * 插入书籍
     * @param book
     * @return
     */
    int insertBook(Book book);

    /**
     * 跟新书籍信息
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 条件查询
     * @param condition
     * @return
     */
    List<Book> findBookByCondition(Condition condition);
}
