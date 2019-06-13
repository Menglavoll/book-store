package com.mengla.bookstore.dao;

import com.mengla.bookstore.model.Book;
import com.mengla.bookstore.model.Condition;

import java.util.List;

public interface IBookDao {
    /**
     * 查询所有书籍
     * @return
     */
    List<Book> searchBooks();

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Book> searchBooksByLikeName(String name);

    /**
     * 根据id查询
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
     * @param currentPage
     * @param pageSize
     * @param category
     * @return
     */
    List<Book> searchBooksByPage(int currentPage, int pageSize, String category, String bookName);

    /**
     * 添加书籍
     * @param book
     * @return
     */
    int insertBook(Book book);

    /**
     * 更新书籍信息
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
