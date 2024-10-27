package org.sopt.diary.repository;

public enum Category {
    TRAVEL("여행"),
    DAILY("일상");

    private final String koreanName;

    Category(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
