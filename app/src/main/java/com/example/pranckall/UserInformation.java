package com.example.pranckall;

public class UserInformation {
    private String name;
    private String phone;
    private String userid;

    public UserInformation() {

    }

    public UserInformation(String name, String phone, String userid) {
        this.name = name;
        this.phone = phone;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
