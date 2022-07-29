package app.myoun.kut.controller

import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.service.AdminService
import app.myoun.kut.utils.FailedResponse
import app.myoun.kut.utils.Response
import app.myoun.kut.utils.SuccessfulResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name="Admin Controller", description = "For Admin Panel")
@ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = SuccessfulResponse::class))])
@RestController
class AdminController(val adminService: AdminService) {

    /**
     * 1. 수용 한도 설정
     * 2. 날짜별 쓰레기 투입 수 확인
     */

    @PostMapping("/admins/validate")
    fun validateAdmin(@RequestBody validateDto: ValidateDto): Response {
        val isValid = adminService.validateAdmin(validateDto) ?: return FailedResponse(404, "cannot find admin account")
        return SuccessfulResponse(200, isValid)
    }

    @PostMapping("/admins/admin")
    fun createAdmin(@RequestBody accountDto: AccountDto): Response {
        val admin = adminService.createAdmin(accountDto) ?: return FailedResponse(409, "admin already exists")
        return SuccessfulResponse(200, admin)
    }


}