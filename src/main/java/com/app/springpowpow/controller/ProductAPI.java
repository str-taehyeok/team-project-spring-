package com.app.springpowpow.controller;

import com.app.springpowpow.domain.*;
import com.app.springpowpow.service.DeliveryService;
import com.app.springpowpow.service.ProductFileService;
import com.app.springpowpow.service.ProductService;
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
@RequestMapping("/products/*")
public class ProductAPI {
    private final ProductService productService;
    private final DeliveryService deliveryService;
    private final ProductFileService productFileService;

    //    제품 등록
    @Operation(summary = "제품 등록", description = "제품등록 API")
    @ApiResponse(responseCode = "200", description = "제품등록 완료")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productName", description = "제품명", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productPrice", description = "제품가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productRealPrice", description = "제품소비자가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productCode", description = "제품 코드", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productStock", description = "제품 재고", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productDetail", description = "제품 상세정보", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productFileName", description = "제품 이미지", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productFilePath", description = "제품 이미지", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productAnimal", description = "반려동물 종류", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productCategory", description = "제품 카테고리", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productColor", description = "제품 펫스널 컬러", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productSize", description = "제품 사이즈", schema = @Schema(type = "char"), in = ParameterIn.HEADER),
    })
    @PostMapping("write")
    public void insert(
            @RequestParam("uuids") String[] uuids,
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

    ) {

//        vo담아서 작성하면 되잖아요 이 사람아
        Map<String, String> response = new HashMap<String, String>();
        ProductVO productVO = new ProductVO();
        DeliveryVO deliveryVO = new DeliveryVO();
        ProductFileVO productFileVO = new ProductFileVO();
        deliveryVO.setDeliveryCompany(deliveryCompany);
        deliveryVO.setDeliveryFee(deliveryFee);
        deliveryVO.setDeliveryFeeFree(deliveryFeeFree);
        deliveryVO.setDeliveryHow(deliveryHow);
        deliveryVO.setDeliveryPayWhen(deliveryPayWhen);
        productVO.setProductAnimal(productAnimal);
        productVO.setProductCategory(productCategory);
        productVO.setProductColor(productColor);
        productVO.setProductDetail(productDetail);
        productVO.setProductName(productName);
        productVO.setProductPrice(productPrice);
        productVO.setProductRealPrice(productRealPrice);
        productVO.setProductStock(productStock);
//        productFileVO.setProductFileName(uuids[0] + "_" + uploadFiles.get(0).getOriginalFilename());
//        productFileVO.setProductFilePath(getPath());
        for (int i = 0; i < uploadFiles.size(); i++) {
            if (!uploadFiles.get(i).isEmpty()) {
                productFileVO.setProductFileName(uuids[i] + "_" + uploadFiles.get(i).getOriginalFilename());
                productFileVO.setProductFilePath(getPath());
                productFileService.insertNewImage(productFileVO);
            }

            productService.insertNewProduct(productVO);
            deliveryService.insertDeliveryInfo(deliveryVO);
//            productFileService.insertNewImage(productFileVO);

        }

        response.put("message", "제품 등록 완료");
        log.info(response.toString());


    }

    @Operation(summary = "제품 전체 조회", description = "모든 제품을 리스트로 볼수 있는 API")
    @GetMapping("products")
    public List<ProductDTO> products() {
        return productService.selectAllProducts();
    }

    @Operation(summary = "동물 종에 따른 제품 전체 조회", description = "종에 따라 모든 제품을 리스트로 볼수 있는 API")
    @GetMapping("products/{productAnimal}")
    public List<ProductDTO> products(@PathVariable String productAnimal) {
        return productService.selectProductsByAnimal();
    }

    //    판매자별 상품 전체 조회
    @Operation(summary = "판매자별 상품 전체 리스트", description = "판매자별 상품 전체 볼 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "회원 id", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productName", description = "제품명", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productPrice", description = "제품가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productRealPrice", description = "제품소비자가격", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productCode", description = "제품 코드", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "productStock", description = "제품 재고", schema = @Schema(type = "number"), in = ParameterIn.HEADER),
            @Parameter(name = "productDetail", description = "제품 상세정보", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productAnimal", description = "반려동물 종류", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productCategory", description = "제품 카테고리", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productColor", description = "제품 펫스널 컬러", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "productSize", description = "제품 사이즈", schema = @Schema(type = "char"), in = ParameterIn.HEADER),
    })
    @GetMapping("seller-all-list/{memberId}")
    public List<ProductDTO> getList(@PathVariable Long memberId) {
        return productService.selectAllProductsBySellerId(memberId);
    }


//  단일 제품
    @Operation(summary = "제품 단일 조회", description = "제품 한가지를 볼수 있는 API")
    @GetMapping("product/{id}")
    public Optional<ProductDTO> product(@PathVariable Long id){
        return productService.selectProductById(id);
    }

//    제품 수정
    @Operation(summary = "제품 수정", description = "제품을 수정하는 API")
    @Parameter(name = "id", description = "제품 수정", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "제품 수정 완료")
    @PutMapping("seller-product/{id}")
    public ProductDTO modify(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        productService.updateProduct(productDTO);
        return productDTO;
    }

//    제품 삭제
    @Operation(summary = "제품 삭제", description = "제품을 삭제하는 API")
    @Parameter(name = "id", description = "제품 번호", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
    @ApiResponse(responseCode = "200", description = "제품 삭제 완료")
    @DeleteMapping("seller-product/{deleteProductId}")
    public void delete(@PathVariable Long deleteProductId) {
        System.out.println("삭제할 제품 ID: " + deleteProductId); // ID가 정상적으로 출력되는지 확인
        productService.deleteProduct(deleteProductId);
    }

//    멤버나가면 제품다 삭제
@Operation(summary = "제품 삭제", description = "제품을 삭제하는 API")
@Parameter(name = "id", description = "제품 번호", schema = @Schema(type="number"))//DB의 스키마가 아니라, swagger에서 인식하기 위한 타입in = ParameterIn.PATH, //path 로 전달required = true //반드시 전달)
@ApiResponse(responseCode = "200", description = "제품 삭제 완료")
@DeleteMapping("seller-products/{memberId}")
public void deleteProductsBySellerId(@PathVariable Long memberId){
        productService.deleteAllProducts(memberId);
}


// 사진 등록
    @PostMapping("image-upload")
    public ResponseEntity<Map<String, Object>> fileUpload(
            @RequestParam("title") String title,
            @RequestParam("files") List<MultipartFile> files
    ) {

        Map<String, Object> response = new HashMap<String, Object>();
        log.info("title {}", title);
        for (MultipartFile file : files) {
            log.info("file {}", file.getOriginalFilename());
        }

        response.put("uuid", UUID.randomUUID().toString());
        return ResponseEntity.ok(response);
    }


    @GetMapping("detail")
    public void goToDetail(Model model) {
        model.addAttribute("files", productFileService.getList());
    }


    //    현재 시간을 기준으로 년월일로 관리 할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

//    사진 수정
    @PutMapping("image/{id}")
    public ProductFileVO update(@PathVariable Long id, @RequestBody ProductFileVO productFileVO) {
        productFileVO.setId(id);
        productFileService.updateImage(productFileVO);
        return productFileVO;
    }

//    제품 삭제시 사진도 삭제
    @DeleteMapping("image/{id}")
    public void deleteImage(@PathVariable Long id) {
        productFileService.deleteImage(id);
    }




}
