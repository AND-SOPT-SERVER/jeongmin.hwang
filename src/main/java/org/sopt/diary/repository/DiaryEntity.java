package org.sopt.diary.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;

    public DiaryEntity() {
    }

    public DiaryEntity(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
