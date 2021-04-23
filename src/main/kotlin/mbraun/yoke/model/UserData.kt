package mbraun.yoke.model

import java.util.*
import javax.persistence.*
import javax.validation.constraints.*


@Table(name = "user_data")
@Entity
data class UserData(
    @Column(name = "id")
    @Id
    private val id: UUID,

    // TODO: Add password constraint
    @Column(name = "password")
    private val password: String,

    // TODO: add password conformation

    @Column(name = "userName")
    @NotNull
    @NotBlank(message =  "UserName can not be null")
    var userName: String,

    @Column(name = "email")
    @Email
    @NotNull
    @NotEmpty
    val email: String,

    @Column(name = "age")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    var age: Int,

    @Column(name = "gender")
    @NotNull
    @NotBlank
    var gender: Enum<Gender>,

    @Column(name = "role")
    @NotNull
    @NotBlank
    var role: Enum<Role>
)