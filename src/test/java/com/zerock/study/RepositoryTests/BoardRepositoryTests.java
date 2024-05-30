package com.zerock.study.RepositoryTests;


import com.zerock.study.Repository.BoardRepository;
import com.zerock.study.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


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

    @Test
    public void testSearchAll2(){
        String[] types = {"t","w","c"}; //3가지 검색 조건을 다 넣겠다.

        String keyword = "1"; //1이란 숫자가 들어간 t w c

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //총 페이지 갯수
        log.info("총 페이지 개수 :  "+result.getTotalPages());

        //총 글  갯수
        log.info("총 게시물 갯수 : "+result.getTotalElements());

        //현재 페이지에 글 갯수
        log.info("현제 페이지에 게시물 갯수 : "+result.getSize());

        //현제 페이지 번호. (index)
        log.info("현제 페이지 번호 : "+result.getNumber());

        //전 페이지 및 다음 페이지 여부
        log.info("전 페이지와 다음페이지 여부 : " + result.hasPrevious() + ":" + result.hasNext());

        //result.getContent()를 통해 현재 페이지의 실제 결과 데이터인 List<Board>를 가져온다.
        result.getContent().forEach(board -> log.info(board));
    }

}
