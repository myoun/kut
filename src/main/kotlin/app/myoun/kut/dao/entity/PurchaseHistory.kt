package app.myoun.kut.dao.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    @OneToOne
    lateinit var user: User

    @OneToOne
    lateinit var product: Product

    @CreatedDate
    lateinit var purchaseAt : LocalDateTime

    @Column
    val isDelivered: Boolean = false

}