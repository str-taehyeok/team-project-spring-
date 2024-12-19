package com.app.springpowpow.controller;

import com.app.springpowpow.domain.*;
import com.app.springpowpow.service.PostFileService;
import com.app.springpowpow.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts/*")
public class PostAPI {

    private final PostService postService;
    private final PostFileService postFileService;
    private final PostFileVO postFileVO;

    //    게시글 전체 조회
    @Operation(summary = "게시글 전체 조회", description = "게시글 정보를 전체 조회할 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "게시글 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "postContent", description = "게시글 내용", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "postImage1", description = "게시글 이미지1", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "postImage2", description = "게시글 이미지2", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "postImage3", description = "게시글 이미지3", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "postImage4", description = "게시글 이미지4", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "postImage5", description = "게시글 이미지5", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberId", description = "회원 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberEmail", description = "회원 이메일", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberName", description = "회원 이름", schema = @Schema(type = "string"), in = ParameterIn.HEADER)
    })
    @GetMapping("list")
    public List<PostDTO> getPosts() {
        return postService.getList();
    }


    //    게시글 전체 조회
    @Operation(summary = "게시글 인기글 5개 조회", description = "게시글 인기글 5개 조회할 수 있는 API")
    @GetMapping("postsPopular")
    public List<PostDTO> getPostPopular() {
        return postService.getListPopular();
    }

    //    게시글 단일 조회
    @Operation(summary = "게시글 정보 조회", description = "게시글 정보를 조회할 수 있는 API")
    @Parameter(name = "id", description = "게시글 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @GetMapping("post/{id}")
    public PostDTO getPost(@PathVariable Long id) {
        Optional<PostDTO> foundPost = postService.getPost(id);
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        return new PostDTO();
    }


    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "게시글 새로 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "게시글 작성 완료")
    @PostMapping("write")
    public void insert(
            @RequestParam("uuids") List<String> uuids,
            @RequestParam("memberId") Long memberId,
            @RequestParam("postContent") String postContent,
            @RequestParam("postColor") String postColor,
            @RequestParam("uploadFile") List<MultipartFile> uploadFiles
            ) {


        int count = 0;
        for (int i = 0; i < uploadFiles.size(); i++) {
            if (uploadFiles.get(i).isEmpty()) {
                count++;
                continue;
            }
        log.info("요청");
        log.info("a {}", uuids.toString());
        log.info("b {}", memberId.toString());
        log.info("c {}", postContent);
        log.info("d {}", postColor);
        log.info("e {}", uploadFiles);

            PostVO postVO = new PostVO();
            PostFileVO postFileVO = new PostFileVO();

            postVO.setMemberId(memberId);
            postVO.setPostContent(postContent);
            postVO.setPostColor(postColor);
            postService.save(postVO);

            postFileVO.setPostId(postVO.getId());
            postFileVO.setPostFileName(uuids.get(i - count) + "_" + uploadFiles.get(i).getOriginalFilename());
            postFileVO.setPostFilePath(getPath());
            log.info(postVO.toString());
            log.info(postFileVO.toString());
            postFileService.insertNewImage(postFileVO);
        }

    }

    //    현재 시간을 기준으로 년월일로 관리 할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }


    //    게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글 수정할 수 있는 API")
    @Parameter(name = "id", description = "게시글 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "게시글 수정 완료")
    @PutMapping("post/{id}")
    public PostDTO modify(@PathVariable Long id, @RequestBody PostVO postVO) {
        postVO.setId(id);
        postService.modify(postVO);
        Optional<PostDTO> foundPost = postService.getPost(postVO.getId());
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        return new PostDTO();
    }


    //    게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글 삭제할 수 있는 API")
    @Parameter(name = "id", description = "게시글 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "게시글 삭제 완료")
    @DeleteMapping("post/{id}")
    public void delete(@PathVariable Long id) {
        postService.remove(id);
    }

}