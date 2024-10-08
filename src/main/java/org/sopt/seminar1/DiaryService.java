package org.sopt.seminar1;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();
    private Map<LocalDate, Integer> patchCheck= new HashMap<>();

    Long savePost(String input){
        return this.diaryRepository.savePost(input);
    }

    void deletePost(Long id){
        this.diaryRepository.deletePost(id);
    }

    void getPost(Long id){
        this.diaryRepository.getPost(id);
    }

    List<Diary> getAllPost(){
        return this.diaryRepository.getAllPost();
    }

    void updatePost(Long id, String body){
        LocalDate today = LocalDate.now();
        if (!this.patchCheck.containsKey(today)) {
            this.patchCheck.put(today, 0);
        }

        if (this.patchCheck.get(today) > 2){
            throw new RuntimeException("patch는 하루에 2번까지만 가능합니다.");
        }

        this.diaryRepository.updatePost(id, body);
        Integer patchCheck = this.patchCheck.get(today);
        this.patchCheck.put(today, patchCheck + 1);
    }

    void restorePost(Long id){
        String oldPost = this.diaryRepository.getPostFromTrashCan(id);
        this.diaryRepository.savePost(oldPost);
    }
}
