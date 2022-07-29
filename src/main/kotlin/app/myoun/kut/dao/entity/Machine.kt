package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
class Machine {

    @Id
    @GeneratedValue
    var id: Long? = null

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="admin_seq")
    lateinit var admin: Admin




}