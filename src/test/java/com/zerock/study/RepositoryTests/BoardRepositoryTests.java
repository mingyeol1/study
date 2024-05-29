package com.zerock.study.RepositoryTests;


import com.zerock.study.Repository.BoardRepository;
import com.zerock.study.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTests(){

        IntStream.rangeClosed(1,100).forEach(i->{
        Board board = Board.builder()
                .title("테스트 타이틀" + i)
                .writer("테스트 작성자"+(i % 10))
                .content("테스트 내용넣기"+i)
                .build();

        boardRepository.save(board);
        log.info(board);
        });
    }

    @Test
    public void selectTests(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void updateTests(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("나머지연산","개어렵네 하");

        boardRepository.save(board);
        log.info("바뀐 제목과 내용 : " + board);

    }

    @Test
    public void deleteTests(){
        Long bno = 100L;
        boardRepository.deleteById(bno);

        log.info(bno);
    }

}
