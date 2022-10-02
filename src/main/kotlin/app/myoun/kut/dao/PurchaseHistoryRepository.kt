package app.myoun.kut.dao

import app.myoun.kut.dao.entity.PurchaseHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseHistoryRepository : JpaRepository<PurchaseHistory, Long> {

}