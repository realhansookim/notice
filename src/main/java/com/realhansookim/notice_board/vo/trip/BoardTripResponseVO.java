package com.realhansookim.notice_board.vo.trip;

import java.util.List;

import com.realhansookim.notice_board.entity.BoardTripInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardTripResponseVO {
    private List<BoardTripInfoEntity> list;

    private Long total;
   
    private Integer totalPage;
    
    private Integer currentPage; 
}
