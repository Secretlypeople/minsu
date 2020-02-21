package com.jk.dto;

public class UserInfo {

    private Integer id;
    private Integer userId;
    private String userName;
    private String img;
    private String phone;
    private  String email;
    private String sigcard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSigcard() {
        return sigcard;
    }

    public void setSigcard(String sigcard) {
        this.sigcard = sigcard;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", img='" + img + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sigcard='" + sigcard + '\'' +
                '}';
    }
}
