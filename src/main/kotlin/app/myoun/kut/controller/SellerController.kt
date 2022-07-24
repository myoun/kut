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

    @GetMapping("/sellers/{id}/products")
    fun getSellerProducts(@PathVariable("id") sellerId: String): Any {
        val seller = sellerService.getSeller(sellerId) ?: return mapOf("status" to 404, "message" to "cannot find seller")
        return mapOf("status" to 200, "data" to sellerService.getProducts(seller))
    }

    @PostMapping("/sellers/seller")
    fun createSeller(@RequestBody sellerDto: UserDto): Any {
        val seller = sellerService.createSeller(sellerDto)  ?: return mapOf("status" to 409, "message" to "seller already exists")
        return mapOf("status" to 200, "data" to seller)
    }

    @PostMapping("/sellers/{id}/product")
    fun createProduct(@PathVariable("id") id: String, @RequestBody productDto: ProductDto): Any {
        val seller = sellerService.getSeller(id) ?: return mapOf("status" to 404, "message" to "cannot find seller")
        val product = sellerService.addProduct(seller, productDto)
        return mapOf("status" to 200, "data" to product)
    }

}
