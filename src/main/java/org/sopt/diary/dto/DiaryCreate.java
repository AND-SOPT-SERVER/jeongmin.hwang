package org.sopt.diary.dto;

public class DiaryCreate {
    public String name;
    public String title;
    public String content;

    public DiaryCreate(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public DiaryCreate() { }

    public String getName(){
        return this.name;
    }

    public String getTitle(){
        return this.title;
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
}
