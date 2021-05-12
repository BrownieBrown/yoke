package mbraun.yoke.payload.request

import javax.validation.constraints.NotBlank

data class LoginRequest (
    @NotBlank(message = "Username can not be empty")
    var username: String = "",

    @NotBlank(message = "Password can not be empty")
    var password: String = ""
)
