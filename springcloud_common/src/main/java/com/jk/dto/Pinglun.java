package com.jk.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_pingluns")
public class Pinglun {

    private String id;
    private String content;
    private String createTime;
    private Integer sotyId;
    private Integer userId;
    private  String userName;
    private String img;
    private int like;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getSotyId() {
        return sotyId;
    }

    public void setSotyId(Integer sotyId) {
        this.sotyId = sotyId;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
