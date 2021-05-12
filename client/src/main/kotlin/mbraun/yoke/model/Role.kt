package mbraun.yoke.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @Column(unique = true, name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    var name: ERole = ERole.VIEWER
) {
    fun getName(): String {
        return name.toString()
    }
}
