package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.Type
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@DynamicInsert
class Product {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length = 32, nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    var price: Int = 0

    @Column(columnDefinition = "mediumtext", nullable = false)
    var thumbnail_url: String = ""

    @Column(columnDefinition = "varchar(125) default ''")
    var description: String = ""

    @Column(columnDefinition = "mediumtext", nullable = false)
    var content: String = ""

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_seq")
    var seller: Seller? = null

}

