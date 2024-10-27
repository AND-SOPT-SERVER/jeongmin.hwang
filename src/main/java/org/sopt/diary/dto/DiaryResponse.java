package org.sopt.diary.dto;

import org.sopt.diary.repository.Category;

public class DiaryResponse {
    private long id;
    private String name;
    public Category category;

    public DiaryResponse(long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
