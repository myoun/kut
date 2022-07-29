package app.myoun.kut.controller

import app.myoun.kut.dto.PointDto
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.service.UserService
import app.myoun.kut.utils.SuccessfulResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name="User Controller", description = "About Users")
@ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = SuccessfulResponse::class))])
@RestController
class UserController(val userService: UserService) {

    @Operation(summary = "유저 조회")
    @GetMapping("/users/{id}")
    fun getUser(@PathVariable("id") id : String): Any {
        val user = userService.getUserInfo(id) ?: return mapOf("status" to 404, "message" to "cannot find user")
        return mapOf("status" to 200, "data" to user)
    }

    @Operation(summary = "유저 생성")
    @PostMapping("/users/user")
    fun createUser(@RequestBody accountDto: AccountDto): Any {
        val user = userService.createUser(accountDto) ?: return mapOf("status" to 409, "message" to "user already exists")
        return mapOf("status" to 200, "data" to user)
    }

    @Operation(summary = "포인트 설정")
    @PostMapping("/users/{id}/point")
    fun setPoint(@PathVariable("id") id: String, @RequestBody pointDto: PointDto) : Any {
        val isSuccess = userService.updatePoint(id, pointDto.point)
        return if (isSuccess) mapOf("status" to 200) else mapOf("status" to 404, "message" to "cannot find user")
    }

    @Operation(summary = "유저 확인")
    @PostMapping("/users/validate")
    fun validateUser(@RequestBody validateDto: ValidateDto): Any {
        val isValid = userService.validateUser(validateDto) ?: return mapOf("status" to 404, "message" to "cannot find user")
        return mapOf("status" to 200, "data" to isValid)
    }
}