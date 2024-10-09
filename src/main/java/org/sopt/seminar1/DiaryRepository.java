package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private Map<Long, String> storage;
    private final Map<Long, String> trashCan = new ConcurrentHashMap<>();
    private final AtomicLong numbering;

    public DiaryRepository() {
        DiaryLocalRepository local = new DiaryLocalRepository();
        System.out.println("localget: " + local.localGet());
        this.storage = local.localGet();
        Long maxKey = this.storage.keySet().stream()
                .max(Long::compare)
                .orElse(null);
        if (maxKey == null) {
            maxKey = 0L;
        }
        this.numbering = new AtomicLong(maxKey);
    }

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
