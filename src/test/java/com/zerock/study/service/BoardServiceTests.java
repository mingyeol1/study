package com.zerock.study.service;

import com.zerock.study.Repository.BoardRepository;
import com.zerock.study.domain.Board;
import com.zerock.study.dto.BoardDTO;
import com.zerock.study.dto.PageRequestDTO;
import com.zerock.study.dto.PageResponseDTO;
import com.zerock.study.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;



    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("게시판 제목 등록 확인")
                .content("게시판 내용 등록 확인")
                .writer("게시판 작성자 등록 확인")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno : "+ bno);
    }

    @Test
    public void testReadOne(){
        Long bno = 102L;
      BoardDTO boardDTO = boardService.readOne(bno);

    log.info("boardDTO "+ boardDTO);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(102L)
                .title("게시판 제목 등록 바꾸기")
                .content("게시판 내용 등록 바꾸기")
                .build();
        boardService.modify(boardDTO);
        log.info("boardDTO "+ boardDTO);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }




}
