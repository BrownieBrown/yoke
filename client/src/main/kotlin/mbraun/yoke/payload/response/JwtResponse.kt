package mbraun.yoke.payload.response

import java.util.*

data class JwtResponse(
    var accessToken: String,
    var id: UUID,
    var username: String,
    var email: String,
    val roles: List<String>,
    var tokenType: String = "Bearer"
)