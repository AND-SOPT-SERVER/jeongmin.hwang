package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final Map<Long, String> trashCan = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    Long savePost(String input){
        final long id = numbering.addAndGet(1);
        storage.put(id, input);
        return id;
    }

    void deletePost(Long id){
        trashCan.put(id, storage.get(id));
        storage.remove(id);
    }

    void clearTrashCan(){
        trashCan.clear();
    }

    String getPost(Long id){
        return storage.get(id);
    }

    String getPostFromTrashCan(Long id){
        return trashCan.get(id);
    }

    List<Diary> getAllPost(){
        List<Diary> diaryList= new ArrayList<Diary>();
        for(long i = 1; i <= numbering.intValue(); i++){
            if (storage.get(i)!= null){
                diaryList.add(new Diary(i, storage.get(i)));
            }
        }
        return diaryList;
    }

    void updatePost(Long id, String body){
        storage.put(id, body);
    }
}
