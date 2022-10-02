package app.myoun.kut.service

import app.myoun.kut.dao.ProductRepository
import app.myoun.kut.dao.PurchaseHistoryRepository
import app.myoun.kut.dao.UserRepository
import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.PurchaseHistory
import app.myoun.kut.dao.entity.User
import app.myoun.kut.dto.AccountDto
import app.myoun.kut.dto.ValidateDto
import app.myoun.kut.utils.encryptSHA256
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val productRepository: ProductRepository, val purchaseHistoryRepository: PurchaseHistoryRepository) {

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

    fun updatePointById(id : String, point : Int) : User? {
        val user = userRepository.findByIdOrNull(id) ?: return null
        return updatePoint(user, point)
    }

    fun updatePoint(user: User, point: Int): User {
        return userRepository.save(user.apply { this.point = point })
    }

    fun purchaseProduct(user: User, product: Product): PurchaseHistory {
        val newUser = updatePoint(user, user.point - product.price)
        val history = PurchaseHistory().apply {
            this.user = newUser
            this.product = product
        }
        return purchaseHistoryRepository.save(history)
    }
}