package app.myoun.kut.dao.entity

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

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
    lateinit var admin: Admin

}