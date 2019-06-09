package com.mengla.bookstore.service.impl;

import com.mengla.bookstore.dao.IBookDao;
import com.mengla.bookstore.model.Book;
import com.mengla.bookstore.model.Condition;
import com.mengla.bookstore.model.PageBooks;
import com.mengla.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookDao bookDao;

    /**
     * 查询所有书籍
     * @return
     */
    @Override
    public List<Book> searchBooks() {
        return bookDao.searchBooks();
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public List<Book> searchBooksByLikeName(String name) {
        return bookDao.searchBooksByLikeName(name);
    }

    /**
     * 通过id查询
     * @param bookId
     * @return
     */
    @Override
    public Book searchBookById(long bookId) {
        return bookDao.searchBookById(bookId);
    }

    /**
     * 通过类别查询
     * @param category
     * @return
     */
    @Override
    public List<Book> searchBooksByCategory(String category) {
        return bookDao.searchBooksByCategory(category);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBooks searchBooksByPage(int currentPage,int pageSize,String category,String bookName) {
        // 得到总记录数
        int count = 0;

        if("".equals(bookName)){
            count = bookDao.searchBooksByCategory(category).size();
        }else {
            count = bookDao.searchBooksByLikeName(bookName).size();
        }

        // 得到总页数
        int totalPage = (int)Math.ceil(count*1.0/pageSize);

        List<Book> books = bookDao.searchBooksByPage((currentPage-1)*pageSize,pageSize,category,bookName);

        PageBooks pageBooks = new PageBooks();
        pageBooks.setBooks(books);
        pageBooks.setCount(count);
        pageBooks.setCurrentPage(currentPage);
        pageBooks.setPageSize(pageSize);
        pageBooks.setTotalPage(totalPage);

        return pageBooks;
    }

    /**
     * 添加书籍
     * @param book
     * @return
     */
    @Override
    public int insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    /**
     * 条件查询
     * @param condition
     * @return
     */
    @Override
    public List<Book> findBookByCondition(Condition condition) {
        return bookDao.findBookByCondition(condition);
    }
}
