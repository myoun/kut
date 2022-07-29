package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 어드민
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class Admin {

    @Id
    @Column(length = 16)
    lateinit var id: String

    @Column(length = 16)
    lateinit var name: String

    @JsonIgnore
    @Column(length = 64)
    lateinit var password: String

    @CreatedDate
    lateinit var createAt : LocalDateTime

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
    var machines: MutableList<Machine> = ArrayList()

    fun addMachine(machine: Machine) {
        machines.add(machine)
        machine.admin = this
    }

}