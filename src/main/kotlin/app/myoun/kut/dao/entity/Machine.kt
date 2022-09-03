package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Type
import java.math.BigDecimal
import javax.persistence.*

@Entity
class Machine {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length=16)
    var name: String? = null

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="admin_seq")
    lateinit var admin: Admin

    @Type(type = "big_decimal")
    @Column(name="input_limit",precision = 3, scale = 2)
    var limit: BigDecimal = BigDecimal.valueOf(0.7)


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "machine")
    var trashes: MutableList<MachineTrash> = ArrayList()

}