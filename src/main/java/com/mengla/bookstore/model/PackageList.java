package com.mengla.bookstore.model;

/**
 * 包裹实体类
 * 属性：包裹id packageId、订单id orderId、书籍id bookId、
 * 书籍名称bookName、书籍数量num、小计count
 */
public class PackageList {
    private long packageId;
    private long orderId;
    private long bookId;
    private String bookName;
    private int num;
    private double count;

    @Override
    public String toString() {
        return "PackageList{" +
                "packageId=" + packageId +
                ", orderId=" + orderId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", num=" + num +
                ", count=" + count +
                '}';
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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
