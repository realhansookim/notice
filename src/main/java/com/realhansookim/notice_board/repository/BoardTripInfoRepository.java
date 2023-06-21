package com.realhansookim.notice_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.realhansookim.notice_board.entity.BoardTripInfoEntity;

public interface BoardTripInfoRepository extends JpaRepository<BoardTripInfoEntity,Long> {
    Page<BoardTripInfoEntity> findByBtiTitleContains(String btiTitle, Pageable pageable);
}
