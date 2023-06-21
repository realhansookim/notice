package com.realhansookim.notice_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.realhansookim.notice_board.entity.AdminEntity;




@Repository
public interface AdminRepository extends JpaRepositoryImplementation<AdminEntity, Long> {
    public AdminEntity findByAdminId(String adminId);
    public AdminEntity findByAdminIdAndAdminPwd(String adminId, String adminPwd);
    public Integer countByAdminId(String adminId);
    public Page<AdminEntity> findByAdminIdContains(String adminId, Pageable pageable);
}
