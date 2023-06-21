package com.realhansookim.notice_board.vo;

import java.util.List;

import com.realhansookim.notice_board.entity.BoardMusicInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMusicResponseVO {
        private List<BoardMusicInfoEntity> list;
       
        private Long total;
       
        private Integer totalPage;
   
        private Integer currentPage;
    }

