package com.suda.backend.service.board;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suda.backend.entity.Board;
import com.suda.backend.repository.BoardRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
    
    private BoardRepository boardRepository;

    public List<Board> list() {
        List<Board> list = boardRepository.findAll();
        
        return list;
    }

    public BoardDTO view(Long id) throws Exception {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) {
            return new BoardDTO(board.get());
        } else {
            throw new Exception("리소스를 찾을 수 없습니다.");
        }
    }

    public BoardDTO post(BoardDTO model) {
        Board result = boardRepository.save(model.toEntity());
        return new BoardDTO(result);
    }

    public BoardDTO update(BoardDTO model, Long id) throws Exception {
        BoardDTO boarddto = this.view(id);
        
        boarddto.setTitle(model.getTitle());
        boarddto.setContent(model.getContent());
        boarddto.setId(id);
        boarddto.setFileId(model.getFileId());
        Board result = boardRepository.save(boarddto.toEntity());

        return new BoardDTO(result);
    }

    public void delete(Long id) throws Exception {
        this.view(id);
        boardRepository.deleteById(id);
    }

}