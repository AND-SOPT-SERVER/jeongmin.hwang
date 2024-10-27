package org.sopt.diary.dto;

import java.util.Date;

public class DiaryUpdate {
    public String title;
    public String content;
    public Date updatedAt;

    public DiaryUpdate(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = new Date();
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }
}
