package org.sopt.diary.dto;

import java.util.Date;

public class DiaryDetailResponse {
    private long id;
    private String name;
    private String title;
    private String content;
    private Date createdAt;

    public DiaryDetailResponse(long id, String name, String title, String content, Date createdAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
