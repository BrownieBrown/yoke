package mbraun.yoke.security

import mbraun.yoke.model.Role
import mbraun.yoke.model.Role.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfiguration(@Autowired private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/registration", "/").permitAll()
            .antMatchers("/api/**").hasAnyRole(PRIMARY_OWNER.name, OWNER.name, ADMIN.name)
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val michaelJordan = User.builder()
            .username("AirJordan")
            .password(passwordEncoder.encode("GOAT"))
            .roles(OWNER.name)
            .build()

        val lebronJames = User.builder()
            .username("KingJames")
            .password(passwordEncoder.encode("WannabeGOAT"))
            .roles(VIEWER.name)
            .build()

        val dirkNowitzki = User.builder()
            .username("GermanJesus")
            .password(passwordEncoder.encode("MAVS4LIFE"))
            .roles(VIEWER.name)
            .build()

        return InMemoryUserDetailsManager(michaelJordan, lebronJames, dirkNowitzki)
    }
}