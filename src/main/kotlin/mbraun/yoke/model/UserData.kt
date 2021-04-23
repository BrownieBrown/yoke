package mbraun.yoke.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.*

@Entity
data class TestUserData(
    @Id
    private val id: UUID,

    // TODO: Add password constraint
    private val password: String,

    // TODO: add password conformation

    @NotNull
    @NotBlank(message =  "UserName can not be null")
    var userName: String,

    @Email
    @NotNull
    @NotEmpty
    private val email: String,

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    var age: Int,

    @NotNull
    @NotBlank
    var gender: Enum<Gender>,

    @NotNull
    @NotBlank
    var roles: Enum<Role>
)