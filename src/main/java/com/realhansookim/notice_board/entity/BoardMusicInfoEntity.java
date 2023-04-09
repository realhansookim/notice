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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Entity
@Table(name = "board_music_info")
public class BoardMusicInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bmi_seq") private Long bmiSeq;
    @Column(name = "bmi_title") private String bmiTitle;
    @Column(name = "bmi_content") private String bmiContent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "bmi_reg_dt") @ColumnDefault(value = "current_timestamp") private LocalDate bmiRegDt;
    @Column(name = "bmi_img") private String bmiImg;    
}
