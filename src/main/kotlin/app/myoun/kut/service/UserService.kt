package app.myoun.kut.service

import app.myoun.kut.dao.UserRepository
import app.myoun.kut.dao.entity.User
import app.myoun.kut.dto.UserDto
import app.myoun.kut.dto.UserValidateDto
import app.myoun.kut.utils.encryptSHA256
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getUserInfo(id: String) : User? {
        return userRepository.findByIdOrNull(id)
    }

    fun createUser(userDto: UserDto): User? {
        if (!userRepository.findById(userDto.id).isEmpty) {
            return null
        }

        val user = User().apply {
            id = userDto.id
            name = userDto.name
            password = userDto.password.encryptSHA256()
        }

        return userRepository.save(user)
    }

    fun validateUser(userValidateDto: UserValidateDto): Boolean? {
        val user = getUserInfo(userValidateDto.id) ?: return null
        return user.password == userValidateDto.password.encryptSHA256()
    }

    fun updatePoint(id : String, point : Int) : Boolean {
        val user = userRepository.findByIdOrNull(id) ?: return false
        user.point = point
        userRepository.save(user)
        return true
    }
}