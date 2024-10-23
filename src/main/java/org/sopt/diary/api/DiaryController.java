package org.sopt.diary.api;

import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.DiaryUpdate;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/diary")
    ResponseEntity<List<DiaryResponse>> getDiary() {
        return ResponseEntity.ok(diaryService.getList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/diary")
    void createDiary(@RequestBody DiaryEntity diaryEntity) {
        if (diaryEntity.getContent().length() > 30) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Content is too long");
        }
        diaryService.createDiary(diaryEntity);
    }

    @GetMapping("/diary/{id}")
    ResponseEntity<DiaryDetailResponse> getOneDiary(@PathVariable final long id) {
        return ResponseEntity.ok(diaryService.getOne(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/diary/{id}")
    void updateDiary(@PathVariable final long id, @RequestBody DiaryUpdate diaryUpdate) {
        diaryService.updateDiary(id, diaryUpdate);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/diary/{id}")
    void updateDiary(@PathVariable final long id) {
        diaryService.deleteDiary(id);
    }
}
