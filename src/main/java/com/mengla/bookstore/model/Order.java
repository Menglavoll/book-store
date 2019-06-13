package com.mengla.bookstore.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 * 属性：订单id orderId、用户id userId 总价total、状态state、
 * 创建时间createTime、支付时间paymentTime、收件人receiver、
 * 收件人手机号recMobile、收件地址：address
 */
public class Order implements Serializable {
    private long orderId;
    private long userId;
    private double total;
    private int state;
    private Date createTime;
    private Date paymentTime;
    private String receiver;
    private String recMobile;
    private String address;
    private int orderPackage;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", total=" + total +
                ", state=" + state +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", receiver='" + receiver + '\'' +
                ", recMobile='" + recMobile + '\'' +
                ", address='" + address + '\'' +
                ", orderPackage=" + orderPackage +
                '}';
    }

    public int getOrderPackage() {
        return orderPackage;
    }

    public void setOrderPackage(int orderPackage) {
        this.orderPackage = orderPackage;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRecMobile() {
        return recMobile;
    }

    public void setRecMobile(String recMobile) {
        this.recMobile = recMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
