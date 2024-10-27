package org.sopt.diary.dto;

import org.sopt.diary.repository.Category;

public class DiaryCreate {
    public String name;
    public String title;
    public Category category;
    public String content;

    public DiaryCreate(String name, String title, Category category, String content) {
        this.name = name;
        this.title = title;
        this.category = category;
        this.content = content;
    }

    public DiaryCreate() { }

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

    public void setContent(String content){
        this.content = content;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}
