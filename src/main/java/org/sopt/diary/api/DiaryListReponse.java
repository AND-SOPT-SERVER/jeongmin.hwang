package org.sopt.diary.api;

import java.util.List;

public class DiaryListReponse {
    private List<DiaryResponse> diaryList;

    public DiaryListReponse(List<DiaryResponse> diaryList) {
        this.diaryList = diaryList;
    }

    public List<DiaryResponse> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<DiaryResponse> diaryList) {
        this.diaryList = diaryList;
    }
}
