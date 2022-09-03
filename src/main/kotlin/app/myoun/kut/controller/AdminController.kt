package app.myoun.kut.controller

import app.myoun.kut.dao.entity.Admin
import app.myoun.kut.dao.entity.Machine
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.MachineDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.service.AdminService
import app.myoun.kut.utils.ErrorMessage
import app.myoun.kut.utils.ValidationResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.streams.toList

@Tag(name="Admin Controller", description = "For Admin Panel")
@RestController
class AdminController(val adminService: AdminService) {

    /**
     * 1. 수용 한도 설정
     * 2. 날짜별 쓰레기 투입 수 확인
     */

    @Operation(summary = "관리자 확인")
    @PostMapping("/admins/validate")
    fun validateAdmin(@RequestBody validateDto: ValidateDto): ResponseEntity<ValidationResponse> {
        val isValid = adminService.validateAdmin(validateDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(ValidationResponse(isValid))
    }

    @Operation(summary = "관리자 생성")
    @PostMapping("/admins/admin")
    fun createAdmin(@RequestBody accountDto: AccountDto): ResponseEntity<Admin> {
        val admin = adminService.createAdmin(accountDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(admin)
    }

    @Operation(summary = "관리자 조회")
    @GetMapping("/admins/{id}")
    fun getAdmin(@PathVariable("id") id: String): ResponseEntity<Admin> {
        val admin = adminService.getAdmin(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(admin)
    }

    @Operation(summary = "기계 조회")
    @GetMapping("/admins/{id}/machines")
    fun getMachines(@PathVariable("id") adminId: String): ResponseEntity<List<Machine>> {
        val admin = adminService.getAdmin(adminId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(admin.machines)
    }

    @PostMapping("/admins/{id}/machine")
    fun addMachine(@PathVariable("id") id: String, @RequestBody machineDto: MachineDto): ResponseEntity<Machine> {
        val admin = adminService.getAdmin(id) ?: return ResponseEntity.notFound().build()
        val machine = adminService.addMachine(admin, machineDto)
        return ResponseEntity.ok(machine)
    }




}