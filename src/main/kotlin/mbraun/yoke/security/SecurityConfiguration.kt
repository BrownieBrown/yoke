package mbraun.yoke.security

import mbraun.yoke.model.Permissions
import mbraun.yoke.model.Permissions.*
import mbraun.yoke.model.Role
import mbraun.yoke.model.Role.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(@Autowired private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/registration", "/").permitAll()
            .antMatchers("/api/**").hasAnyRole(PRIMARY_OWNER.name, OWNER.name, ADMIN.name)
//            .antMatchers("/user_management/api/v1/userData/delete_all/**").hasRole(PRIMARY_OWNER.name)
//            .antMatchers(DELETE, "/user_management/api/v1/userData/delete_user/**").hasAuthority(REMOVE_USER.permission)
//            .antMatchers(POST, "/user_management/api/v1/userData/add_user/**").hasAuthority(INVITE_NEW_USER.permission)
//            .antMatchers(PATCH, "/user_management/api/v1/userData/appoint_admin/**").hasAuthority(APPOINT_ADMINS.permission)
//            .antMatchers(PATCH, "/user_management/api/v1/userData/demote_admin/**").hasAuthority(DEMOTE_ADMINS.permission)
//            .antMatchers(PATCH, "/user_management/api/v1/userData/appoint_owner/**").hasAuthority(APPOINT_OWNERS.permission)
//            .antMatchers(PATCH, "/user_management/api/v1/userData/demote_owner/**").hasAuthority(DEMOTE_OWNERS.permission)
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
//            .roles(OWNER.name)
            .authorities(OWNER.getGrantedAuthorities())
            .build()

        val lebronJames = User.builder()
            .username("KingJames")
            .password(passwordEncoder.encode("WannabeGOAT"))
//            .roles(ADMIN.name)
            .authorities(ADMIN.getGrantedAuthorities())
            .build()

        val dirkNowitzki = User.builder()
            .username("GermanJesus")
            .password(passwordEncoder.encode("MAVS4LIFE"))
//            .roles(VIEWER.name)
            .authorities(VIEWER.getGrantedAuthorities())
            .build()

        return InMemoryUserDetailsManager(michaelJordan, lebronJames, dirkNowitzki)
    }
}