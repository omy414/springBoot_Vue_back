package com.suda.backend.service.board;

import com.suda.backend.entity.Board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {
    
    private Long id;
    private String title;
    private String content;
    private Long fileId;

    public Board toEntity() {
        return Board.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .fileId(fileId)
                    .build();
    }

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.fileId = board.getFileId();
    }
}
