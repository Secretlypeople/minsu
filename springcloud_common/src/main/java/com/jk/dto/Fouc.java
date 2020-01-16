package com.jk.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_fouc")
public class Fouc {

    private String id;
    private  Integer userId;
    private Integer selfId;
    private String createDate;
    private Integer count;

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

    public Integer getSelfId() {
        return selfId;
    }

    public void setSelfId(Integer selfId) {
        this.selfId = selfId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Fouc{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", selfId=" + selfId +
                ", createDate='" + createDate + '\'' +
                ", count=" + count +
                '}';
    }
}
