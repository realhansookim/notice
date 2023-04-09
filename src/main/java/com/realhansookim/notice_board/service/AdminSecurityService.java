package com.realhansookim.notice_board.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.realhansookim.notice_board.entity.AdminEntity;
import com.realhansookim.notice_board.repository.AdminRepository;
import com.realhansookim.notice_board.security.provider.JwtTokenProvider;
import com.realhansookim.notice_board.security.service.CustomUserDetailService;
import com.realhansookim.notice_board.vo.admin.AdminInfoVO;
import com.realhansookim.notice_board.vo.admin.AdminLoginVO;
import com.realhansookim.notice_board.vo.admin.response.AdminLoginResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminSecurityService {
    private final AdminRepository adminRepository;
    private final AuthenticationManagerBuilder authBuilder;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailService userDetailService;
    
    public AdminLoginResponseVO login (AdminLoginVO login){
        AdminEntity admin = adminRepository.findByAdminIdAndAdminPwd(login.getId(), login.getPwd());
        AdminLoginResponseVO response = null;
        if(admin == null){
            return AdminLoginResponseVO.builder().status(false).message("아이디 또는 비밀번호 오류입니다.")
            .code(HttpStatus.FORBIDDEN).build();        
        }
        else if(!admin.isEnabled() ){
            return AdminLoginResponseVO.builder().status(false).message("이용정지된 사용자 입니다.")
            .code(HttpStatus.FORBIDDEN).build();
        }
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(admin.getAdminId(), admin.getAdminPwd());
        Authentication authentication = authBuilder.getObject().authenticate(authenticationToken);
        return AdminLoginResponseVO.builder().status(true).message("정상로그인").code(HttpStatus.OK)
        .token(tokenProvider.generateToken(authentication)).build();
    }
    public AdminInfoVO getAdminDetailInfo(String id){
        try{
            userDetailService.loadUserByUsername(id);
            AdminEntity entity = adminRepository.findByAdminId(id);
            AdminInfoVO vo = new AdminInfoVO(entity);
            return vo;
        }
        catch(UsernameNotFoundException e){
            return null;
        }
    }
}
