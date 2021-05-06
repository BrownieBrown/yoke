package mbraun.yoke.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.sql.Date
import java.time.LocalDate
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UsernameAndPasswordAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        try {
            val authenticationRequest =
                ObjectMapper().readValue(request.inputStream, UsernameAndPasswordAuthenticationRequest::class.java)

            val authentication = UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )

            return authenticationManager.authenticate(authentication)

        } catch (e: AuthenticationException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {

        val key = "4Xtjtnse7XWC9xmtC9MyB9iukVAEzNPti4VDCZ6c6EgbA"
        val token = Jwts.builder()
            .setSubject(authResult.name)
            .claim("authorities", authResult.authorities)
            .setIssuedAt(java.util.Date())
            .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
            .signWith(Keys.hmacShaKeyFor(key.toByteArray()))
            .compact()

        response.addHeader("Authorization", "Bearer $token")
    }
}