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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(@Autowired private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            //Implement for Front End
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/registration", "/").permitAll()
            .antMatchers("/api/**").hasAnyRole(PRIMARY_OWNER.name, OWNER.name, ADMIN.name)
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/user", true)
            .passwordParameter("password")
            .usernameParameter("username")
            .and()
            .rememberMe()
            .tokenValiditySeconds(TimeUnit.DAYS.toSeconds(21).toInt())
            .key("somethingverysecured")
            .rememberMeParameter("remember-me")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutRequestMatcher(AntPathRequestMatcher("/logout", "GET"))
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "remember-me")
            .logoutSuccessUrl("/login")
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