package com.realhansookim.notice_board.vo.admin.response;

import org.springframework.http.HttpStatus;

import com.realhansookim.notice_board.security.vo.TokenVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginResponseVO {
    private Boolean status;
    private String message;
    private HttpStatus code;
    private TokenVO token;
}
