package pictures.reisinger.demo

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity

@Entity
class MyKotlinEntity : PanacheEntity() {
    companion object : PanacheCompanion<MyKotlinEntity> {
        fun byName(name: String) = list("name", name)
    }

    lateinit var name: String
}
