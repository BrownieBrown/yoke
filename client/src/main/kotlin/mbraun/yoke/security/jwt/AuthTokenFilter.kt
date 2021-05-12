package mbraun.yoke.security.jwt

import mbraun.yoke.security.services.UserDetailsServiceImplementation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter(@Autowired private val jwtUtils: JwtUtils, @Autowired private val userDetailsService: UserDetailsServiceImplementation, @Autowired private val jwtConfig: JwtConfig ) : OncePerRequestFilter() {

    private fun parseJwt(request: HttpServletRequest): String {
        val authorizationHeader = request.getHeader(jwtConfig.authorizationHeader)
        return authorizationHeader.replace(jwtConfig.tokenPrefix, "")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwtToken = parseJwt(request)

            if (jwtUtils.validateJwtToken(jwtToken)) {
                val username = jwtUtils.getUserNameFromJwtToken(jwtToken)
                val userDetails = userDetailsService.loadUserByUsername(username)
                val authorities = userDetails.authorities
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, authorities)

                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            }
        } catch (e: Exception) {
            val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)
            logger.error("Cannot set user authentication: $e")
        }
    }
}