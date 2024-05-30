package com.zerock.study.Repository;

import com.zerock.study.Repository.search.BoardSearch;
import com.zerock.study.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
        // JPA가 제공하는 쿼리메서드
        Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

        //JPQL 쿼리메서드
        @Query("select b from Board b where b.title like concat('%',:keyword,'%') ")
        Page<Board> findByword(String keyword, Pageable pageable);

        //네이티브 쿼리 데이터베이스 고유의 SQL 문법을 사용하여 쿼리를 작성
        @Query(value = "select new()", nativeQuery = true)
        String getTime();
}
