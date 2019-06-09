package com.mengla.bookstore.model;

/**
 * 购物车实体类
 * 属性：用户id UserId、书籍id bookId、
 * 书籍名称bookName、书籍数量num、小计count
 */
public class Cart {
    private long userId;
    private long bookId;
    private String bookName;
    private int num;
    private double count;

    @Override
    public String toString() {
        return "cart{" +
                "userId=" + userId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", num=" + num +
                ", count=" + count +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
