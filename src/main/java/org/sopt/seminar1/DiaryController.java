package org.sopt.seminar1;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getAllPost();
    }

    final void post(final String body) {
        if(body.length() > 30){
            throw new RuntimeException("30자 이내로 작성해주세요.");
        }
        this.diaryService.savePost(body);
    }

    final void delete(final String id) {
        this.diaryService.deletePost(Long.parseLong(id));
    }

    final void patch(final String id, final String body) {
        this.diaryService.updatePost(Long.parseLong(id), body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}