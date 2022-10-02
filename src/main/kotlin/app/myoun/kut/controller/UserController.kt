package app.myoun.kut.controller

import app.myoun.kut.dao.entity.PurchaseHistory
import app.myoun.kut.dao.entity.User
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.PointDto
import app.myoun.kut.dto.PurchaseDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.service.SellerService
import app.myoun.kut.service.UserService
import app.myoun.kut.utils.ValidationResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name="User Controller", description = "About Users")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
class UserController(val userService: UserService, val sellerService: SellerService) {

    @Operation(summary = "유저 조회")
    @GetMapping("/users/{id}")
    fun getUser(@PathVariable("id") id : String): ResponseEntity<User> {
        val user = userService.getUserInfo(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @Operation(summary = "유저 생성")
    @PostMapping("/users/user")
    fun createUser(@RequestBody accountDto: AccountDto): ResponseEntity<User> {
        val user = userService.createUser(accountDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @Operation(summary = "포인트 설정")
    @PostMapping("/users/{id}/point")
    fun setPoint(@PathVariable("id") id: String, @RequestBody pointDto: PointDto) : ResponseEntity<User> {
        val user = userService.updatePointById(id, pointDto.point) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @Operation(summary = "유저 확인")
    @PostMapping("/users/validate")
    fun validateUser(@RequestBody validateDto: ValidateDto): ResponseEntity<ValidationResponse> {
        val isValid = userService.validateUser(validateDto) ?: return ResponseEntity.status(409).build()
        return ResponseEntity.ok(ValidationResponse(isValid))
    }

    @Operation(summary = "상품 구매")
    @PostMapping("/users/purchase")
    fun purchaseProduct(@RequestBody purchaseDto: PurchaseDto): ResponseEntity<PurchaseHistory> {
        val user = userService.getUserInfo(purchaseDto.user_id) ?: return ResponseEntity.status(404).build()
        val product = sellerService.getProductByProductId(purchaseDto.product_id) ?: return ResponseEntity.status(404).build()

        if (user.point < product.price) {
            return ResponseEntity.status(422).build()
        }

        val history = userService.purchaseProduct(user, product)

        return ResponseEntity.ok(history)
    }
}