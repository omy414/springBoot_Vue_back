package com.suda.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.suda.backend.entity.Board;
import com.suda.backend.repository.BoardRepository;

@SpringBootTest
class BackendApplicationTests {

	// private final BoardRepository boardRepository;

    // // 의존성 주입
    // @Autowired
    // public BackendApplicationTests(BoardRepository boardRepository) {
    //     this.boardRepository = boardRepository;
    // }

    // @Test
    // void boardTest() {
    //     // 멤버 저장
	// 	System.out.println("실행입니다.");
    //     Board board = new Board();
    //     board.setName("nameTestAAAAAA");
    //     boardRepository.save(board);

    //     // 저장한 멤버 아이디로 검색
    //     Board findBoard = boardRepository.findById(board.getId()).get();
    //     System.out.println(findBoard.getName());
    // }


	@Test
	void contextLoads() {
	}

}
