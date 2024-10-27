package org.sopt.diary.service;

import org.sopt.diary.api.DiaryController;
import org.sopt.diary.dto.DiaryCreate;
import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.DiaryUpdate;
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
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Title is already exist");
        });
        DiaryEntity diaryEntity = new DiaryEntity(diaryCreate.getName(), diaryCreate.getTitle(), diaryCreate.getContent());
        diaryRepository.save(diaryEntity);
    }

    public void updateDiary(long id, DiaryUpdate diaryUpdate) {
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        final DiaryEntity diaryEntity = diaryEntityOptional.get();

        if (Math.abs(new Date().getTime() - diaryEntity.getUpdatedAt().getTime()) < 5 * 60 * 1000) {
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
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        diaryRepository.deleteById(id);
    }

    public List<DiaryResponse> getList(){
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        final List<DiaryResponse> diaryList = new ArrayList<>();
        diaryEntityList.sort(Comparator.comparing(DiaryEntity::getCreatedAt).reversed());
        int cnt = 0;
        for(DiaryEntity diaryEntity : diaryEntityList){
            if (cnt >= 10){
                break;
            }
            diaryList.add(new DiaryResponse(diaryEntity.getId(), diaryEntity.getName()));
            cnt++;
        }
        return diaryList;
    }

    public DiaryDetailResponse getOne(long id){
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        final DiaryEntity diaryEntity = diaryEntityOptional.get();
        System.out.println("updated at: " + diaryEntity.getUpdatedAt());
        return new DiaryDetailResponse(diaryEntity.getId(), diaryEntity.getName(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getCreatedAt());
    }
}
