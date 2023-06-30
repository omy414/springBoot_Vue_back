package com.suda.backend.controller;

import java.io.File;
import java.util.List;

import com.suda.backend.config.ApiResult;
import com.suda.backend.entity.Board;
import com.suda.backend.service.board.BoardDTO;
import com.suda.backend.service.board.BoardService;
import com.suda.backend.service.file.FileDTO;
import com.suda.backend.service.file.FileService;
import com.suda.backend.util.MD5Generator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
public class BoardController {

    BoardService boardService;
    FileService fileService;

    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }

    @GetMapping("/api/boards")
    public ApiResult<List<Board>> getAll() {
        List<Board> list = boardService.list();

        return ApiResult.<List<Board>>builder()
                .data(list)
                .build();
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable("id") Long id) {
        ResponseEntity<?> entity = null;

        try {
            entity = new ResponseEntity<BoardDTO>(boardService.view(id), HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        return entity;
    }

    @PostMapping("/api/boards/post")
    public ResponseEntity<?> create(@RequestParam(value = "file", required = false) MultipartFile files, BoardDTO boarddto) {
        
        try {

            if(files != null){
                String origFilename = files.getOriginalFilename();
                String filename = new MD5Generator(origFilename).toString();
                String savePath = "D:/00_minyoung/daouProject/backend/upload/files/";

                if (!new File(savePath).exists()) {
                    try{
                        new File(savePath).mkdir();
                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }

                String filePath = savePath + "\\" + filename;
                files.transferTo(new File(filePath));

                FileDTO fileDto = new FileDTO();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                Long fileId = fileService.saveFile(fileDto);
                boarddto.setFileId(fileId);
            }
            
            return new ResponseEntity<BoardDTO>(boardService.post(boarddto), HttpStatus.CREATED);
        } catch (final Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/api/boards/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BoardDTO boarddto) {
        try {
            return new ResponseEntity<BoardDTO>(boardService.update(boarddto, id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/api/boards/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        
        ResponseEntity<?> entity = null;

        try {
            boardService.delete(id);
            entity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entity;
    }

}
