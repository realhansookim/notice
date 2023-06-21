package com.realhansookim.notice_board.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_trip_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class BoardTripInfoEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bti_seq") private Long btiSeq;
    @Column(name = "bti_title") private String btiTitle;
    @Column(name = "bti_content") private String btiContent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "bti_reg_dt") @ColumnDefault(value = "current_timestamp") private LocalDate btiRegDt;
    @Column(name = "bti_img") private String btiImg; 

}
