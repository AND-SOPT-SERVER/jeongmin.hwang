package org.sopt.seminar1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DiaryLocalRepository {
    void localSave(Map<Long, String> diary) {
        Gson gson = new Gson();
        File file = new File("src/main/java/org/sopt/seminar1/diary.json");

        if (!file.exists()) {
            try {
                file.createNewFile(); // 파일 생성
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(diary, writer);
            System.out.println("Map이 JSON 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Map<Long, String> localGet() {
        File file = new File("src/main/java/org/sopt/seminar1/diary.json");
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다. 빈 Map을 반환합니다.");
            return new HashMap<>();
        }

        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<Long, String>>() {}.getType();
            Map<Long, String> loadedMap = gson.fromJson(reader, mapType);
            return loadedMap != null ? loadedMap : new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
