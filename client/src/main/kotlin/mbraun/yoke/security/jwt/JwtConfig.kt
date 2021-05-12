package mbraun.yoke.security.jwt

import com.google.common.net.HttpHeaders.AUTHORIZATION
import org.springframework.stereotype.Component

@Component
data class JwtConfig (
    val secretKey: String = "4Xtjtnse7XWC9xmtC9MyB9iukVAEzNPti4VDCZ6c6EgbA",
    val tokenPrefix: String = "Bearer ",
    val tokenExpirationAfterDays: Long = 14,
    val authorizationHeader: String = AUTHORIZATION
)