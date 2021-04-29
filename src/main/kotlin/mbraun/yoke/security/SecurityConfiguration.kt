package mbraun.yoke.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(@Autowired private val passwordEncoder: PasswordEncoder): WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/registration", "/")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }
}