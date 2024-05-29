package com.zerock.study.Repository;

import com.zerock.study.Repository.search.BoardSearch;
import com.zerock.study.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
        Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

        @Query("select b from Board b where b.title like concat('%',:keyword,'%') ")
        Page<Board> findByword(String keyword, Pageable pageable);

        @Query(value = "select new()", nativeQuery = true)
        String getTime();
}
