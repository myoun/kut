package app.myoun.kut.controller

import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.Seller
import app.myoun.kut.dto.*
import app.myoun.kut.service.SellerService
import app.myoun.kut.utils.ValidationResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name="Seller Controller", description = "About Sellers & Products")
@CrossOrigin("*")
@RestController
class SellerController(val sellerService: SellerService) {

    @Operation(summary = "판매자 조회")
    @GetMapping("/sellers/{id}")
    fun getSeller(@PathVariable("id") id: String): ResponseEntity<Seller> {
        val seller =  sellerService.getSeller(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(seller)
    }

    @Operation(summary = "상품 조회")
    @GetMapping("/sellers/{id}/products")
    fun getSellerProducts(@PathVariable("id") sellerId: String): ResponseEntity<List<Product>> {
        val seller = sellerService.getSeller(sellerId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(seller.products)
    }

    @Operation(summary = "상품 가져오기")
    @GetMapping("/sellers/products")
    fun getProducts(pageable: Pageable): List<ProductResponse> {
        return sellerService.getProducts(pageable).content
    }

    @Operation(summary = "판매자 생성")
    @PostMapping("/sellers/seller")
    fun createSeller(@RequestBody accountDto: AccountDto): ResponseEntity<Seller> {
        val seller = sellerService.createSeller(accountDto)  ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(seller)
    }
    @Operation(summary = "상품 생성")
    @PostMapping("/sellers/{id}/product")
    fun createProduct(@PathVariable("id") id: String, @RequestBody productDto: ProductDto): ResponseEntity<Product> {
        val seller = sellerService.getSeller(id) ?: return ResponseEntity.notFound().build()
        val product = sellerService.addProduct(seller, productDto)
        return ResponseEntity.ok(product)
    }

    @Operation(summary = "판매자 확인")
    @PostMapping("/sellers/validate")
    fun validateSeller(@RequestBody validateDto: ValidateDto): ResponseEntity<ValidationResponse> {
        val isValid = sellerService.validateSeller(validateDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(ValidationResponse(isValid))
    }
}