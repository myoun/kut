package app.myoun.kut.dao

import app.myoun.kut.dao.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<Admin, String> {


}