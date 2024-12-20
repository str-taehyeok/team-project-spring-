package com.app.springpowpow.controller;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/seller/*")
public class SellerUpdateAPI {

    private final MemberService memberService;

//    판매자 정보 수정
    @Operation(summary = "판매자 정보 수정", description = "판매자 수정할 수 있는 API")
    @Parameter( name = "id", description = "판매자 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "회원 수정 완료")
    @PutMapping("update/{id}")
    public MemberVO modify(@PathVariable Long id, @RequestBody MemberVO memberVO){
        memberVO.setId(id);
        memberService.modify(memberVO);
        Optional<MemberVO> foundMember = memberService.getMemberById(memberVO.getId());
        if (foundMember.isPresent()){
            return foundMember.get();
        }
        return new MemberVO();
    }


//    판매자 정보 단일조회
//    memberAPI꺼 가져와서 사용
    @Operation(summary = "판매자 정보 조회", description = "판매자 정보를 조회할 수 있는 API")
    @Parameter(name = "id", description = "회원 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true)
    @GetMapping("/seller-info/{id}")
    public MemberVO getMemberById(@PathVariable Long id){
        Optional<MemberVO> findById = memberService.getMemberById(id);
        if (findById.isPresent()){
            return findById.get();
        }
        return new MemberVO();
    }

    //  회원탈퇴(판매자)
    @Operation(summary = "판매자회원탈퇴", description = "회원정보 탈퇴할 수 있는 API")
    @Parameter(name = "id", description = "판매자 회원 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "회원정보 탈퇴 완료")
    @DeleteMapping("/seller-info/{id}")
    public void deleteSeller(@PathVariable Long id) {
        memberService.withdrawSeller(id);

    }


}
