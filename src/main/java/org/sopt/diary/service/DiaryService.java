package org.sopt.diary.service;

import org.sopt.diary.dto.DiaryCreate;
import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.DiaryUpdate;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(DiaryCreate diaryCreate) {
        DiaryEntity diaryEntity = new DiaryEntity(diaryCreate.getName(), diaryCreate.getTitle(), diaryCreate.getContent());
        diaryRepository.save(diaryEntity);
    }

    public void updateDiary(long id, DiaryUpdate diaryUpdate) {
        final Optional<DiaryEntity> diaryEntityOptional = diaryRepository.findById(id);
        if (diaryEntityOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        final DiaryEntity diaryEntity = diaryEntityOptional.get();
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
        return new DiaryDetailResponse(diaryEntity.getId(), diaryEntity.getName(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getCreatedAt());
    }
}
