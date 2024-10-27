package org.sopt.diary.service;

import org.sopt.diary.dto.DiaryCreate;
import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.DiaryUpdate;
import org.sopt.diary.repository.Category;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(DiaryCreate diaryCreate) {
        this.diaryRepository.findByTitle(diaryCreate.getTitle()).ifPresent(diaryEntity -> {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "이미 존재하는 제목입니다.");
        });
        DiaryEntity diaryEntity = new DiaryEntity(diaryCreate.getName(), diaryCreate.getTitle(), diaryCreate.getCategory(), diaryCreate.getContent());
        diaryRepository.save(diaryEntity);
    }

    public void updateDiary(long id, DiaryUpdate diaryUpdate) {
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "해당 일기가 존재하지 않습니다.");
        }

        final DiaryEntity diaryEntity = diaryEntityOptional.get();

        if (diaryEntity.getUpdatedAt() != null && Math.abs(new Date().getTime() - diaryEntity.getUpdatedAt().getTime()) < 5 * 60 * 1000) {
            throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS, "조금 뒤에 다시 시도해주세요");
        }

        if (diaryUpdate.getTitle() != null) {
            diaryEntity.setTitle(diaryUpdate.getTitle());
        }
        else if(diaryUpdate.getContent() != null) {
            diaryEntity.setContent(diaryUpdate.getContent());
        }
        else{
            return;
        }
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(long id) {
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "해당 일기가 존재하지 않습니다.");
        }
        diaryRepository.deleteById(id);
    }

    public List<DiaryResponse> getList(Category category){
        List<DiaryEntity> diaryEntityList;
        if (category != null){
            diaryEntityList = diaryRepository.findByCategory(category);
        }
        else{
            diaryEntityList = diaryRepository.findAll();
        }

        final List<DiaryResponse> diaryList = new ArrayList<>();
        diaryEntityList.sort(Comparator.comparing(DiaryEntity::getCreatedAt).reversed());
        int cnt = 0;
        for(DiaryEntity diaryEntity : diaryEntityList){
            if (cnt >= 10){
                break;
            }
            diaryList.add(new DiaryResponse(diaryEntity.getId(), diaryEntity.getName(), diaryEntity.getCategory()));
            cnt++;
        }
        return diaryList;
    }

    public DiaryDetailResponse getOne(long id){
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "해당 일기가 존재하지 않습니다.");
        }
        final DiaryEntity diaryEntity = diaryEntityOptional.get();
        return new DiaryDetailResponse(diaryEntity.getId(), diaryEntity.getName(), diaryEntity.getTitle(), diaryEntity.getCategory(), diaryEntity.getContent(), diaryEntity.getCreatedAt());
    }
}
