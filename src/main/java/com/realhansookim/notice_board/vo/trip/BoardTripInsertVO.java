package com.realhansookim.notice_board.vo.trip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardTripInsertVO {
    private String title;
    private String content;
}
