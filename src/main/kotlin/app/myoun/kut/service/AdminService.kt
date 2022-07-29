package app.myoun.kut.service

import app.myoun.kut.dao.AdminRepository
import app.myoun.kut.dao.MachineRepository
import app.myoun.kut.dao.entity.Admin
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.utils.encryptSHA256
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AdminService(val adminRepository: AdminRepository, val machineRepository: MachineRepository) {

    fun validateAdmin(validateDto: ValidateDto): Boolean? {
        val admin = adminRepository.findByIdOrNull(validateDto.id) ?: return null
        return admin.password == validateDto.password.encryptSHA256()
    }

    fun getAdmin(id: String): Admin? {
        return adminRepository.findByIdOrNull(id)
    }

    fun createAdmin(accountDto: AccountDto): Admin? {
        if (getAdmin(accountDto.id) != null) return null

        val admin = Admin().apply {
            id = accountDto.id
            name = accountDto.name
            password = accountDto.password.encryptSHA256()
        }

        return adminRepository.save(admin)
    }
}