package mbraun.yoke.security.jwt

import com.google.common.base.Strings
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenVerifier : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val token = authorizationHeader.replace("Bearer ", "")
            val secretKey = "4Xtjtnse7XWC9xmtC9MyB9iukVAEzNPti4VDCZ6c6EgbA"

            val claimsJws : Jws<Claims> = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray())).parseClaimsJws(token)

            val body = claimsJws.body

            val username = body.subject
            val authorities : List<Map<String, String>> = body.get("authorities") as List<Map<String, String>>

            authorities.stream().map {  }

            val authentication =

            SecurityContextHolder.getContext().authentication

        } catch (e: Exception){

        }
    }
}