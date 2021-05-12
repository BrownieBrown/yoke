package mbraun.yoke.payload.request

import mbraun.yoke.model.ERole
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignupRequest (
    @NotBlank
    @Size(min = 3, max = 20)
    var username: String = "",

    @NotBlank(message = "Email can not be empty")
    @Size(max = 50)
    @Email
    var email: String = "",

    var role: ERole = ERole.VIEWER,

    @NotBlank(message = "Password can not be empty")
    @Size(min = 6, max = 40)
    var password: String = ""
)