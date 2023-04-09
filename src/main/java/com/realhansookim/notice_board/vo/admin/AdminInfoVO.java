package com.realhansookim.notice_board.vo.admin;

import java.time.LocalDate;

import com.realhansookim.notice_board.entity.AdminEntity;
import com.realhansookim.notice_board.entity.MemberInfoEntity;

import lombok.Data;

@Data
public class AdminInfoVO {
    private Long admin_no;
    private String admin_id;
    private String admin_name;
    private LocalDate admin_birth;
    private Integer admin_grade;
    private Integer admin_status;

    public AdminInfoVO(AdminEntity entity){
        this.admin_no = entity.getAdminSeq();
        this.admin_id = entity.getAdminId();
        this.admin_name = entity.getAdminName();
        this.admin_status = entity.getAdminStatus();
        this.admin_grade = entity.getAdminGrade();
    }
}
