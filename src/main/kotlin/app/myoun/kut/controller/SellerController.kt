package app.myoun.kut.controller

import app.myoun.kut.dto.*
import app.myoun.kut.service.SellerService
import app.myoun.kut.utils.FailedResponse
import app.myoun.kut.utils.Response
import app.myoun.kut.utils.SuccessfulResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name="Seller Controller", description = "About Sellers & Products")
@ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = SuccessfulResponse::class))])
@RestController
class SellerController(val sellerService: SellerService) {

    @Operation(summary = "판매자 조회")
    @GetMapping("/sellers/{id}")
    fun getSeller(@PathVariable("id") id: String): Response {
        val seller =  sellerService.getSeller(id) ?: return FailedResponse(404, "cannot find seller")
        return SuccessfulResponse(200, seller)
    }

    @Operation(summary = "상품 조회")
    @GetMapping("/sellers/{id}/products")
    fun getSellerProducts(@PathVariable("id") sellerId: String): Any {
        val seller = sellerService.getSeller(sellerId) ?: return mapOf("status" to 404, "message" to "cannot find seller")
        return mapOf("status" to 200, "data" to seller.products)
    }

    @Operation(summary = "판매자 생성")
    @PostMapping("/sellers/seller")
    fun createSeller(@RequestBody accountDto: AccountDto): Any {
        val seller = sellerService.createSeller(accountDto)  ?: return mapOf("status" to 409, "message" to "seller already exists")
        return mapOf("status" to 200, "data" to seller)
    }
    @Operation(summary = "상품 생성")
    @PostMapping("/sellers/{id}/product")
    fun createProduct(@PathVariable("id") id: String, @RequestBody productDto: ProductDto): Response {
        val seller = sellerService.getSeller(id) ?: return FailedResponse( 404, "cannot find seller")
        val product = sellerService.addProduct(seller, productDto)
        return SuccessfulResponse(200, product)
    }

    @Operation(summary = "판매자 확인")
    @PostMapping("/sellers/validate")
    fun validateSeller(@RequestBody validateDto: ValidateDto): Any {
        val isValid = sellerService.validateSeller(validateDto) ?: return mapOf("status" to 404, "message" to "cannot find seller")
        return mapOf("status" to 200, "data" to isValid)
    }

}
