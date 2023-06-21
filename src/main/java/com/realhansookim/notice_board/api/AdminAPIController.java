package com.realhansookim.notice_board.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realhansookim.notice_board.entity.MemberInfoEntity;
import com.realhansookim.notice_board.repository.MemberInfoRepository;
import com.realhansookim.notice_board.service.AdminSecurityService;
import com.realhansookim.notice_board.vo.admin.AdminInfoVO;
import com.realhansookim.notice_board.vo.admin.AdminLoginVO;
import com.realhansookim.notice_board.vo.admin.response.AdminLoginResponseVO;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAPIController {
    private final AdminSecurityService adminSecurityService;
    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseVO> postAdminLogin(@RequestBody AdminLoginVO login) {
        AdminLoginResponseVO response = adminSecurityService.login(login);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<AdminInfoVO> getAdminDetailInfo(@PathVariable String id) {
        return new ResponseEntity<>(adminSecurityService.getAdminDetailInfo(id), HttpStatus.OK);
    }
}
