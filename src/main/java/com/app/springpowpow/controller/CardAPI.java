package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CardVO;
import com.app.springpowpow.domain.PetDTO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pay/*")
public class CardAPI {

    private final CardService cardService;

    //카드 작성
    @Operation(summary = "카드 작성", description = "카드 작성 API")
    @ApiResponse(responseCode = "200", description = "카드 작성 완료")
    @PostMapping("write")
    public CardVO write(@RequestBody CardVO cardVO) {
        log.info("{}", cardVO.toString());
        cardService.write(cardVO);
        Optional<CardVO> foundCard = cardService.getCard(cardVO.getId());
        if (foundCard.isPresent()) {
            return foundCard.get();
        }
        return new CardVO();
    }
    //카드 조회
    @Operation(summary = "카드 정보 조회", description = "카드 정보를 조회할 수 있는 API")
    @Parameter( name = "id", description = "카드 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @GetMapping("pay/{id}")
    public CardVO getCard(@PathVariable Long id){
        Optional<CardVO> foundCard = cardService.getCard(id);
        if(foundCard.isPresent()){
            return foundCard.get();
        }
        return new CardVO();
    }

    //카드 수정
    @Operation(summary = "카드 수정", description = "카드 수정할 수 있는 API")
    @Parameter( name = "id", description = "카드 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "카드 수정 완료")
    @PutMapping("pay/{id}")
    public CardVO modify(@PathVariable Long id, @RequestBody CardVO cardVO){
        cardVO.setId(id);
        cardService.modify(cardVO);
        Optional<CardVO> foundCard = cardService.getCard(cardVO.getId());
        if(foundCard.isPresent()){
            return foundCard.get();
        }
        return new CardVO();
    }

    //카드 삭제
    @Operation(summary = "카드 삭제", description = "카드 삭제할 수 있는 API")
    @Parameter( name = "id", description = "카드 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "카드 삭제 완료")
    @DeleteMapping("pay/{id}")
    public void delete(@PathVariable Long id){
        cardService.remove(id);
    }

}
