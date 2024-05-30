package com.zerock.study.controller;

import com.zerock.study.dto.BoardDTO;
import com.zerock.study.dto.PageRequestDTO;
import com.zerock.study.dto.PageResponseDTO;
import com.zerock.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }
}
