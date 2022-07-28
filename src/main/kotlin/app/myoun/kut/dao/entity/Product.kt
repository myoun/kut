package app.myoun.kut.dao.entity

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.UUID
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UUID.randomUUID()

    @Column(length = 32)
    lateinit var name: String

    @Column
    var price: Int = 0

    @Column
    lateinit var createdBy: String
}

