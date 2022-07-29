package app.myoun.kut.dao

import app.myoun.kut.dao.entity.Machine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MachineRepository : JpaRepository<Machine, Long> {
}