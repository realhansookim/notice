package com.realhansookim.notice_board.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileService {
    public String saveImageFile(String type, MultipartFile img) throws Exception{
        // 파일이 저장될 폴더의 경로를 가져와서
        Path targetLocation = Paths.get("/musicImage/"+type);
        // 입력된 파일명을 추가하여, 저장될 위치를 설정하고
        String fileName = img.getOriginalFilename();
        String [] split = fileName.split("\\.");
        String ext = split[split.length-1];
        fileName = generateRandomStr()+"."+ext;
        targetLocation = targetLocation.resolve(fileName);
        // 입력된 파일을 저장될 위치에 복사한다.
        Files.copy(img.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        // 저장된 위치를 문자열로 변환해서 내어준다.
        return fileName;
    }

    public ResponseEntity<Resource> getImageFile(String type, String filename) throws Exception{
        // Path imgLocation = Paths.get(location).normalize();
        Path imgLocation = Paths.get("/musicImage/"+type+"/"+filename).normalize();
        Resource r = new UrlResource(imgLocation.toUri());
        String contentType = "applecation/octet-stream";
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\""+URLEncoder.encode(r.getFilename(), "UTF-8"))
        .body(r);
    } 
    
    public static String generateRandomStr() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = (int)Math.floor(Math.random()*5)+6; // 6 ~ 11자
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        Date dt = new Date();
        generatedString += dt.getTime(); // 현재 시간 값 ms 정보를 뒤에 붙임
        System.out.println(generatedString);
        return generatedString;
}
    public Boolean deleteImageFile(String type, String img){
        //파일 폴더의 경로를 가져와서
        Path targetLocation = Paths.get("/musicImage/"+type);
        targetLocation = targetLocation.resolve(img);
        if(!Files.exists(targetLocation)) return false;
        try {
            Files.delete(targetLocation);
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }
}
