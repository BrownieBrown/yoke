package mbraun.yoke.security

import mbraun.yoke.model.Role.*
import mbraun.yoke.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val userService: UserService
) : WebSecurityConfigurerAdapter() {


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

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(daoAuthenticationProvider())
    }

    @Bean
    fun daoAuthenticationProvider() : DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder)
        provider.setUserDetailsService(userService)
        return  provider
    }
}