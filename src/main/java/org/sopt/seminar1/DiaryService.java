package org.sopt.seminar1;

import org.sopt.seminar1.DiaryRepository;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

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
        this.diaryRepository.updatePost(id, body);
    }
}
