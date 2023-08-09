package com.ssafy.ssap.controller;

import com.ssafy.ssap.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("/upload/{pattern}/{id}")
    public ResponseEntity<?> uploadByPattern(@PathVariable String pattern, @PathVariable Integer id, @RequestParam List<MultipartFile> file){
        HttpStatus status;
        status = s3Service.uploadFile(pattern, file, id);
        return new ResponseEntity<>(status);
    }

    @GetMapping("/get/{pattern}/{id}")
    public ResponseEntity<?> getUrl(@PathVariable String pattern, @PathVariable Integer id){
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap;
        switch(pattern){
            case "profile", "alarm", "room" -> resultMap = s3Service.getUrl(pattern, id);
            case "question", "answer" -> resultMap = s3Service.getUrlList(pattern, id);
            default -> {
                resultMap = new HashMap<>();
                resultMap.put("message", "Failed, "+pattern+" 스펠링 확인");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return new ResponseEntity<>(resultMap, status);
    }

//    @Deprecated
//    @GetMapping("/{fileName}")
//    public ResponseEntity<UrlResource> downloadImage(@PathVariable String fileName) {
//        System.out.println(fileName);
//        UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, fileName));
//
//        String contentDisposition = "attachment; filename=\"" +  fileName + "\"";
//
//        // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//                .body(urlResource);
//    }



}