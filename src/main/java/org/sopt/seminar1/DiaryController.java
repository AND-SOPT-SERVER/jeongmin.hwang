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

        try{
            byte[] utf8Bytes = body.getBytes("UTF-8");
            String utf8String = new String(utf8Bytes, "UTF-8");
            if(utf8String.codePointCount(0, utf8String.length()) > 30){
                throw new RuntimeException("30자 이내로 작성해주세요.");
            }
            this.diaryService.savePost(utf8String);
        }
        catch (Exception e){
            throw new RuntimeException("UTF-8 인코딩 에러");
        }
    }

    final void delete(final String id) {
        this.diaryService.deletePost(Long.parseLong(id));
    }

    final void patch(final String id, final String body) {
        if(body.length() > 30){
            throw new RuntimeException("30자 이내로 작성해주세요.");
        }
        this.diaryService.updatePost(Long.parseLong(id), body);
    }

    final void restore(final String id) {
        this.diaryService.restorePost(Long.parseLong(id));
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}