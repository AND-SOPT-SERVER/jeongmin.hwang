package org.sopt.diary.dto;

public class DiaryUpdate {
    public String title;
    public String content;

    public DiaryUpdate(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
}
