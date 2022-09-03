package app.myoun.kut.service

import app.myoun.kut.dao.UserRepository
import app.myoun.kut.dao.entity.User
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.utils.encryptSHA256
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getUserInfo(id: String) : User? {
        return userRepository.findByIdOrNull(id)
    }

    fun createUser(accountDto: AccountDto): User? {
        if (!userRepository.findById(accountDto.id).isEmpty) {
            return null
        }

        val user = User().apply {
            id = accountDto.id
            name = accountDto.name
            password = accountDto.password.encryptSHA256()
        }

        return userRepository.save(user)
    }

    fun validateUser(validateDto: ValidateDto): Boolean? {
        val user = getUserInfo(validateDto.id) ?: return null
        return user.password == validateDto.password.encryptSHA256()
    }

    fun updatePoint(id : String, point : Int) : User? {
        val user = userRepository.findByIdOrNull(id) ?: return null
        user.point = point
        return userRepository.save(user)
    }
}