package mbraun.yoke.security.jwt

data class JwtUsernameAndPasswordAuthenticationRequest(
    val username: String,
    val password: String
) {
}