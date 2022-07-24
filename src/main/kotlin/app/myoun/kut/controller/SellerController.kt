package app.myoun.kut.controller

import app.myoun.kut.dto.ProductDto
import app.myoun.kut.dto.UserDto
import app.myoun.kut.service.SellerService
import org.springframework.web.bind.annotation.*

@RestController
class SellerController(val sellerService: SellerService) {

    @GetMapping("/sellers/{id}")
    fun getSeller(@PathVariable("id") id: String): Any {
        val seller =  sellerService.getSeller(id) ?: return mapOf("status" to 404, "message" to "cannot find seller")
        return mapOf("status" to 200, "data" to seller)
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
