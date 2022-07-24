package app.myoun.kut.dao

import app.myoun.kut.dao.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String> {

    fun findAllByCreatedBy(pageable: Pageable, createdBy: String): Page<Product>
}