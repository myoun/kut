package app.myoun.kut.dao

import app.myoun.kut.dao.entity.Product
import app.myoun.kut.dao.entity.PurchaseHistory
import app.myoun.kut.dao.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseHistoryRepository : JpaRepository<PurchaseHistory, Long> {

    fun findAllByUser(user: User): List<PurchaseHistory>
    fun findAllByProduct(product: Product): List<PurchaseHistory>
}