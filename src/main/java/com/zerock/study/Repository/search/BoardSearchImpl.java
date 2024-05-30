package com.zerock.study.Repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.zerock.study.domain.Board;

import com.zerock.study.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; //Q도메인 객체

        JPQLQuery<Board> query = from(board); // select ... form board

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.title.contains("11")); // title like 11

        booleanBuilder.or(board.content.contains("11")); //content like 11

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));


        query.where(board.title.contains("1")); // where title like  1을 포함하는지

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null) { //검색 조건에 키워드가 있다면 // null값이 아니라면.
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type : types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword)); //타이틀 값 검색
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword)); //컨텐트 값 검색
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword)); //작성자 값 검색
                }
            }// for문 끝
            query.where(booleanBuilder);
        }//if문 끝
        //bno > 0     bno가 0보다 크면 찾는 조건 추가
        query.where(board.bno.gt(0L));

        //페이징
        //applyPagination 메서드를 통해 Pageable 객체의 페이징 정보를 쿼리에 적용
        this.getQuerydsl().applyPagination(pageable, query);

        //query.fetch()를 통해 실제 검색 결과를 List<Board> 형태로 가져온다.
        List<Board> list = query.fetch();

        //query.fetchCount()를 통해 총 결과 개수를 가져온다
        Long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
