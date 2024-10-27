package org.sopt.seminar2.dto;

import org.sopt.seminar2.repository.Category;

import java.util.Date;

public class DiaryDetailResponse {
    private long id;
    private String name;
    private String title;
    public Category category;
    private String content;
    private Date createdAt;

    public DiaryDetailResponse(long id, String name, String title, Category category, String content, Date createdAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
