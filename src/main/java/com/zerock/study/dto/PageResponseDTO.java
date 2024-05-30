package com.zerock.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class PageResponseDTO<E> {

    private int page; //현재 페이지 번호
    private int size; // 한 페이지에 보여줄 게시글 개수
    private int total; // 총 게시물 개수

    //시작페이지 번호
    private int start;

    //마지막 페이지 번호
    private int end;

    //이전 페이지 여부
    private boolean prev;

    //다음 페이지 여부
    private boolean next;

    private List<E> dtoList; // 실제 데이터를 가지고 있는 dto 객체리스트

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        if (total <= 0){
            return;
        }
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10; // 화면에서의 마지막 번호
        this.start = this.end - 9; //화면에서의 시작 번호

        int last = (int)(Math.ceil((total/(double)size))); //데이터 개수를 계산한 마지막 페이지 번호

        this.end = end > last ? last : end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;

    }
}
