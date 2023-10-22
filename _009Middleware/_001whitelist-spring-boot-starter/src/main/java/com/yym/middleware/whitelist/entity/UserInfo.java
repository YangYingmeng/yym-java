package com.yym.middleware.whitelist.entity;

/**
 * @Description: 用户信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-22 20:58
 */
public class UserInfo {

    private String detail;
    private Integer age;
    private String address;

    public UserInfo(String detail, Integer age, String address) {
        this.detail = detail;
        this.age = age;
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public UserInfo setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserInfo setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserInfo setAddress(String address) {
        this.address = address;
        return this;
    }
}
