package com.app.springpowpow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
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


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/*")
public class FileAPI {
    @ResponseBody
    @PostMapping("upload")
    public List<String> upload(
            @RequestParam("deliveryCompany") String deliveryCompany,
            @RequestParam("deliveryFee") int deliveryFee,
            @RequestParam("deliveryFeeFree") int deliveryFeeFree,
            @RequestParam("deliveryFeeKind") String deliveryFeeKind,
            @RequestParam("deliveryHow") String deliveryHow,
            @RequestParam("deliveryPayWhen") String deliveryPayWhen,
            @RequestParam("productAnimal") String productAnimal,
            @RequestParam("productCategory") String productCategory,
            @RequestParam("productColor") String productColor,
            @RequestParam("productDetail") String productDetail,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") int productPrice,
            @RequestParam("productRealPrice") int productRealPrice,
            @RequestParam("productSize") String productSize,
            @RequestParam("productStock") int productStock,
            @RequestParam("uploadFile") List<MultipartFile> uploadFiles
    ) throws IOException {
        String rootPath = "C:/upload/" + getPath();

        List<String> uuids = new ArrayList<>();

//        없으면 폴더 만들기
        File file = new File(rootPath);
        if(!file.exists()){
            file.mkdirs();
        }

        for(int i = 0; i < uploadFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            uploadFiles.get(i).transferTo(new File(rootPath , uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));

//            썸네일
            if(uploadFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadFiles.get(i).getInputStream(), out, 400, 400);
                out.close();
            }
        }
        return uuids;
    }

    //    현재 시간을 기준으로 년월일로 관리 할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        String rootPath = "C:/upload/";
        return FileCopyUtils.copyToByteArray(new File(rootPath + fileName));
    }
}
