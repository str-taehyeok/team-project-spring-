package com.app.springpowpow.controller;

import com.app.springpowpow.domain.ProductLikesDTO;
import com.app.springpowpow.domain.ProductLikesVO;
import com.app.springpowpow.service.ProductLikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kotlin.reflect.KType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/productLikes/*")
public class ProductLikesAPI {

    private final ProductLikesService productLikesService;

//    좋아요 추가
    @Operation(summary = "좋아요 추가", description = "좋아요 추가 API")
    @ApiResponse(responseCode = "200", description = "좋아요 추가 완료")
    @PostMapping("like")
    public void likeProduct(@RequestBody ProductLikesVO productLikesVO) {
        log.info("like {}", productLikesVO.toString());
        productLikesService.addLike(productLikesVO);
    }

//    좋아요 취소
    @Operation(summary = "좋아요 취소", description = "좋아요 취소 API")
    @ApiResponse(responseCode = "200", description = "좋아요 취소 완료")
    @DeleteMapping("cancelLike")
    public void cancelLike(@RequestBody ProductLikesVO productLikesVO) {
        log.info("likeCancel {}", productLikesVO.toString());
        productLikesService.removeLike(productLikesVO);
    }

//    내가 누른 좋아요 제품 보기
    @Operation(summary = "좋아요 제품 조회", description = "좋아요 제품 단일 조회 API")
    @Parameter(name = "memberId", description = "제품 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @GetMapping("LikedProducts")
    public List<ProductLikesDTO> viewLikedProducts(@RequestParam Long memberId) {
        return productLikesService.getLikedProductsByMember(memberId);
    }

//    모든 좋아요 기록 조회
    @Operation(summary = "좋아요 전체 조회", description = "좋아요 전체 조회할 수 있는 API")
    @Parameter(name = "memberId", description = "회원 ID", schema = @Schema(type = "number"), in = ParameterIn.QUERY)
    @PostMapping("allLikes")
    public List<ProductLikesDTO> viewAllLikes(@RequestBody Long memberId) {
        return productLikesService.getAllLikes(memberId);
    }

}

