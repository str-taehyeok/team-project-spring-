package com.app.springpowpow.controller;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/postFiles/*")
public class PostFileAPI {

    // 파일 업로드 처리
    @ResponseBody
    @PostMapping("upload")
    public List<String> upload(
            @RequestParam("memberId") Long memberId,
            @RequestParam("postContent") String postContent,
            @RequestParam("postColor") String postColor,
            @RequestParam("uploadFile") List<MultipartFile> uploadFiles
    ) throws IOException {
        String rootPath = "C:/upload/" + getPath();

        List<String> uuids = new ArrayList<>();

        // 없으면 폴더 만들기
        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        // 업로드된 파일 처리
        for (int i = 0; i < uploadFiles.size(); i++) {
            String uuid = UUID.randomUUID().toString();
            uuids.add(uuid);
            File targetFile = new File(rootPath, uuid + "_" + uploadFiles.get(i).getOriginalFilename());
            uploadFiles.get(i).transferTo(targetFile);

            // 썸네일 생성
            if (uploadFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid + "_" + uploadFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadFiles.get(i).getInputStream(), out, 400, 400);
                out.close();
            }
        }
        log.info("upload path: {}", uuids.toString());
        return uuids;
    }

    // 현재 시간을 기준으로 년월일로 경로 생성
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 업로드된 파일의 내용을 바이너리로 반환
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        String rootPath = "C:/upload/";
        return FileCopyUtils.copyToByteArray(new File(rootPath + fileName));
    }
}
