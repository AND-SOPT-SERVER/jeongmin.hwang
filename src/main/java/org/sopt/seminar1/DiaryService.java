package org.sopt.seminar1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();
    private int patchCheck;

    DiaryService(){
        this.patchCheck = 0;
    }

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
        if (patchCheck > 2){
            throw new RuntimeException("patch는 2번까지만 가능합니다.");
        }
        this.diaryRepository.updatePost(id, body);
        this.patchCheck++;
    }

    void restorePost(Long id){
        String oldPost = this.diaryRepository.getPostFromTrashCan(id);
        this.diaryRepository.savePost(oldPost);
    }
}
