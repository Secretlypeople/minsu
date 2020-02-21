package com.jk.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_fouc")
public class Fouc {

    private String id;
    private  Integer userId;
    private  Integer myUserId;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(Integer myUserId) {
        this.myUserId = myUserId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Fouc{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", myUserId=" + myUserId +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
