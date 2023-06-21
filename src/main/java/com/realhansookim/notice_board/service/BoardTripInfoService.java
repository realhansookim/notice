package com.realhansookim.notice_board.service;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.realhansookim.notice_board.entity.BoardTripInfoEntity;
import com.realhansookim.notice_board.repository.BoardTripInfoRepository;
import com.realhansookim.notice_board.repository.MemberInfoRepository;
import com.realhansookim.notice_board.vo.DeleteResponseVO;
import com.realhansookim.notice_board.vo.trip.BoardTripInsertVO;
import com.realhansookim.notice_board.vo.trip.BoardTripResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardTripInfoService {
    private final FileService fileService;
    private final BoardTripInfoRepository btiRepo;
    private final MemberInfoRepository mRepo;

    public Map<String, Object> addBoardTripInfo(BoardTripInsertVO data, MultipartFile img){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String savedFilePath = "";
        try{
            savedFilePath = fileService.saveImageFile("trip", img);
        }
        catch(Exception e){
            System.out.println("파일 전송 실패");
            resultMap.put("status", false);
            resultMap.put("message", "파일 전송에 실패했습니다.");
            resultMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            return resultMap;
        }
        BoardTripInfoEntity entity = BoardTripInfoEntity.builder()
        .btiTitle(data.getTitle())
        .btiContent(data.getContent())
        .btiImg(savedFilePath)
        .build();
        btiRepo.save(entity);

        resultMap.put("status", true);
        resultMap.put("message", "게시글이 추가되었습니다.");
        resultMap.put("code", HttpStatus.OK);   

        return resultMap;
    }

    public BoardTripResponseVO getBoardTripList(String keyword, Pageable pageable){
        if(keyword == null) keyword = "";
        Page<BoardTripInfoEntity> btiPage = btiRepo.findByBtiTitleContains(keyword, pageable);
        BoardTripResponseVO response = BoardTripResponseVO.builder()
            .list(btiPage.getContent())
            .total(btiPage.getTotalElements())
            .totalPage(btiPage.getTotalPages())
            .currentPage(btiPage.getNumber())
            .build();
        return response;   
    }
    public DeleteResponseVO deleteTripInfo(Long tripNo){
        Optional<BoardTripInfoEntity> entityOpt = btiRepo.findById(tripNo);
        if(entityOpt.isEmpty()){
            return DeleteResponseVO.builder()
            .status(false)
            .message("잘못된 글 번호 입니다.")
            .code(HttpStatus.BAD_REQUEST)
            .build();
        }
        String filename = entityOpt.get().getBtiImg();
        Boolean deleted = fileService.deleteImageFile("trip", filename);
        String message = "해당 글 정보를 삭제하였습니다.";

        if(deleted) message += "(이미지 삭제 완료)";
        else message += "(이미지 삭제 실패)";

        btiRepo.delete(entityOpt.get());
        return DeleteResponseVO.builder().code(HttpStatus.OK).message(message).status(true).build();
    }
    
}
