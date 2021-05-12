package mbraun.yoke.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table(name = "users")
@Entity
data class User(
    @Column(name = "username", unique = true)
    @NotNull
    @Size(max = 20)
    @NotBlank(message =  "Username can not be empty")
    var username: String,

    @Column(name = "email", unique = true)
    @Email
    @NotNull
    @Size(max = 50)
    @NotBlank(message =  "Email can not be empty")
    var email: String,

    @Column(name = "password")
    @Size(max = 120)
    @NotBlank(message =  "Password can not be empty")
    var password: String,

//    @Enumerated(EnumType.STRING)
//    @Column(name = "gender")
//    var gender: EGender,

//    @Column(name = "age")
//    @Min(value = 18, message = "Age should not be less than 18")
//    @Max(value = 150, message = "Age should not be greater than 150")
//    var age: Int,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    private var roles: Set<Role> = HashSet()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    val id: UUID = UUID.randomUUID()

    fun getRoles(): Set<Role> {
        return roles
    }
}