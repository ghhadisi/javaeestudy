package com.hss.mybatis.bean;

public class OrderUser extends Order {
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {

        return "OrderUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}' +super.toString();
    }
}