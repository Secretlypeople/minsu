package com.jk.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_collect")
public class Collect {

    private String id;
    private String classify;//分类
    private String title;//标题
    private String createTime;//创建时间
    private String storyId;//精选故事id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id='" + id + '\'' +
                ", classify='" + classify + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", storyId='" + storyId + '\'' +
                '}';
    }
}
