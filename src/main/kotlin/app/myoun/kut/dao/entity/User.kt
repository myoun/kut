package app.myoun.kut.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id


@Entity
@EntityListeners(AuditingEntityListener::class)
class User {

    @Id
    @Column(length = 16)
    lateinit var id: String

    @Column(length = 16)
    lateinit var name: String

    @JsonIgnore
    @Column(length = 64)
    lateinit var password: String

    @Column
    var point : Int = 0

    @CreatedDate
    lateinit var createAt : LocalDateTime


}