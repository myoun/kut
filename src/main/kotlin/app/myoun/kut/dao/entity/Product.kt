package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Product {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length = 32)
    lateinit var name: String

    @Column
    var price: Int = 0

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_seq")
    var seller: Seller? = null
}

