package com.realhansookim.notice_board.vo;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteResponseVO {
    private Boolean status;
    private String message;
    @JsonIgnore
    private HttpStatus code;
}
