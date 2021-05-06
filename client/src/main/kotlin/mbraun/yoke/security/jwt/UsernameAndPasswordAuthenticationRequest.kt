package mbraun.yoke.security.jwt

data class UsernameAndPasswordAuthenticationRequest(
    val username: String,
    val password: String
) {
}