package org.sopt.seminar2.api;

import org.sopt.seminar2.dto.DiaryCreate;
import org.sopt.seminar2.dto.DiaryDetailResponse;
import org.sopt.seminar2.dto.DiaryResponse;
import org.sopt.seminar2.dto.DiaryUpdate;
import org.sopt.seminar2.repository.Category;
import org.sopt.seminar2.service.DiaryService;
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
    ResponseEntity<List<DiaryResponse>> getDiary(@RequestParam Category category) {
        return ResponseEntity.ok(diaryService.getList(category));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/diary")
    void createDiary(@RequestBody DiaryCreate diaryCreate) {
        if (diaryCreate.getContent().length() > 30) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Content is too long");
        }
        diaryService.createDiary(diaryCreate);
    }

    @GetMapping("/diary/{id}")
    ResponseEntity<DiaryDetailResponse> getOneDiary(@PathVariable final long id) {
        return ResponseEntity.ok(diaryService.getOne(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/diary/{id}")
    void updateDiary(@PathVariable final long id, @RequestBody DiaryUpdate diaryUpdate) {
        if (diaryUpdate.getContent() != null && diaryUpdate.getContent().length() > 30) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Content is too long");
        }
        diaryService.updateDiary(id, diaryUpdate);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/diary/{id}")
    void updateDiary(@PathVariable final long id) {
        diaryService.deleteDiary(id);
    }
}
