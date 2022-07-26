package app.myoun.kut.controller

import app.myoun.kut.dao.entity.User
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.PointDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.service.UserService
import app.myoun.kut.utils.ValidationResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Tag(name="User Controller", description = "About Users")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
class UserController(val userService: UserService) {

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
        val user = userService.updatePoint(id, pointDto.point) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @Operation(summary = "유저 확인")
    @PostMapping("/users/validate")
    fun validateUser(@RequestBody validateDto: ValidateDto): ResponseEntity<ValidationResponse> {
        val isValid = userService.validateUser(validateDto) ?: return ResponseEntity.status(409).build()
        return ResponseEntity.ok(ValidationResponse(isValid))
    }
}