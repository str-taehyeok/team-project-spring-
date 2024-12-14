package com.app.springpowpow.controller;

import com.app.springpowpow.domain.ProductDTO;
import com.app.springpowpow.domain.ReviewDTO;
import com.app.springpowpow.service.ProductService;
import com.app.springpowpow.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewAPI {
    private final ReviewService reviewService;

    @Operation(summary = "제품 등록", description = "제품등록 API")
    @ApiResponse(responseCode = "200", description = "제품등록 완료")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productId", description = "제품 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "reviewStar", description = "별점", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "reviewContent", description = "리뷰", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
    })
    @PostMapping("write")
    public void insert(@RequestBody ReviewDTO reviewDTO) {
        reviewService.insertReview(reviewDTO);
    }

    @Operation(summary = "리뷰 전체 조회", description = "모든 리뷰를 리스트로 볼수 있는 API")
    @GetMapping("reviews/{productId}")
    public List<ReviewDTO> products(@PathVariable Long productId) {
        return reviewService.getReviews(productId);
    }


    //    리뷰 수정
    @Operation(summary = "리뷰 수정", description = "리뷰 수정하는 API")
    @Parameter(name = "id", description = "리뷰 수정", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "리뷰 수정 완료")
    @PutMapping("product-review/{id}")
    public ReviewDTO modify(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setId(id);
        reviewService.updateReview(reviewDTO);
        return reviewDTO;
    }

    //    review 삭제
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제하는 API")
    @Parameter(name = "id", description = "리뷰 삭제", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "리뷰 삭제 완료")
    @DeleteMapping("review/{id}")
    public void delete(@PathVariable Long id){
        reviewService.deleteReview(id);
    }




}
