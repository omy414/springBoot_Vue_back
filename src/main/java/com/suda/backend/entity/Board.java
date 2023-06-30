package com.suda.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.suda.backend.service.Auditor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Board extends Auditor<String> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Long fileId;

    @Builder
    public Board(Long id, String title, String content, Long fileId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
    }
}