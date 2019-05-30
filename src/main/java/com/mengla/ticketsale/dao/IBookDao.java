package com.mengla.ticketsale.dao;

import com.mengla.ticketsale.model.Book;
import com.mengla.ticketsale.model.Condition;

import java.util.List;

public interface IBookDao {
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
     * 根据id查询
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
     * @param currentPage
     * @param pageSize
     * @param category
     * @return
     */
    public List<Book> searchBooksByPage(int currentPage,int pageSize,String category,String bookName);

    /**
     * 添加书籍
     * @param book
     * @return
     */
    public int insertBook(Book book);

    /**
     * 更新书籍信息
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
