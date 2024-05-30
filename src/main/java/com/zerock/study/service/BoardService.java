package com.zerock.study.service;


import com.zerock.study.dto.BoardDTO;
import com.zerock.study.dto.PageRequestDTO;
import com.zerock.study.dto.PageResponseDTO;

public interface BoardService {

    //게시물 등록
    Long register(BoardDTO boardDTO);

    //게시물 조회
    BoardDTO readOne(Long bno);

    //게시물 수정
    void modify(BoardDTO boardDTO);

    //게시물 삭제
    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
