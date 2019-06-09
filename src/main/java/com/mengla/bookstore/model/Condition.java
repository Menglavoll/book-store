package com.mengla.bookstore.model;

/**
 * 用于添加查询的实例
 * 根据bookId、bookName、category、minPrice和maxPrice
 * 几个条件组合来查询
 */
public class Condition {

    private long bookId;
    private String bookName;
    private String category;
    private double minPrice;
    private double maxPrice;

    @Override
    public String toString() {
        return "Condition{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", category='" + category + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
