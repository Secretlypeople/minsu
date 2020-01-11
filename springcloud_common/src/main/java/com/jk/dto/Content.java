package com.jk.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//indexName 操作哪个数据库 type声明哪张表 默认没有type
@Document(indexName = "home",type = "action")
public class Content {

    @Id//声明id
    private Integer id;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String img;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String content;

    @Field(analyzer = "ik_max_word",type = FieldType.Double)
    private Double orprice;//原价

    @Field(analyzer = "ik_max_word",type = FieldType.Double)
    private Double cuprice;//现价


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getOrprice() {
        return orprice;
    }

    public void setOrprice(Double orprice) {
        this.orprice = orprice;
    }

    public Double getCuprice() {
        return cuprice;
    }

    public void setCuprice(Double cuprice) {
        this.cuprice = cuprice;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                ", orprice=" + orprice +
                ", cuprice=" + cuprice +
                '}';
    }
}
