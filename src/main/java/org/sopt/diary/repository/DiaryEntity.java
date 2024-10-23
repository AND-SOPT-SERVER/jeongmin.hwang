package org.sopt.diary.repository;

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
    public String content;
    public Date createdAt;

    public DiaryEntity(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
    }

    public DiaryEntity() {
        this.createdAt = new Date();
    }

    public long getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
