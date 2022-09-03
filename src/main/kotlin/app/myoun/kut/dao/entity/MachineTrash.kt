package app.myoun.kut.dao.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class MachineTrash {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column
    var paper: Long = 0

    @Column
    var plastic: Long = 0

    @Column
    var battery: Long = 0

    @Column
    var can: Long = 0

    @ManyToOne
    @JoinColumn(name = "machine_seq")
    lateinit var machine: Machine

    @Column
    lateinit var day: LocalDateTime

}