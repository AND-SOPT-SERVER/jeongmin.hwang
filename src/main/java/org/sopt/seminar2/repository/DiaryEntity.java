package org.sopt.seminar2.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;


@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String title;
    public Category category;
    public String content;
    public int contentLength;
    public Date createdAt;
    public Date updatedAt;

    public DiaryEntity(String name, String title, Category category, String content) {
        this.name = name;
        this.title = title;
        this.category = category;
        this.content = content;
        this.contentLength = content.length();
        this.createdAt = new Date();
    }

    public DiaryEntity() { }

    public long getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getTitle(){
        return this.title;
    }

    public Category getCategory(){
        return this.category;
    }

    public String getContent(){
        return this.content;
    }

    public int getContentLength(){
        return this.contentLength;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void setContent(String content){
        this.content = content;
        this.contentLength = content.length();
        this.updatedAt = new Date();
    }

    public void setTitle(String title){
        this.title = title;
        this.updatedAt = new Date();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
