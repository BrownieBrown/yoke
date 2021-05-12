package mbraun.yoke.security.services

import com.fasterxml.jackson.annotation.JsonIgnore
import mbraun.yoke.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


class UserDetailsImplementation(
    val id: UUID,
    private var username: String,
    var email: String,
    @JsonIgnore private var password: String,
    private var authorities: List<GrantedAuthority>
) : UserDetails {

     companion object {
         fun build(user: User): UserDetailsImplementation {
             val authorities: List<GrantedAuthority> = user.getRoles()
                 .map { role -> SimpleGrantedAuthority(role.getName()) }

             return UserDetailsImplementation(
                 user.id,
                 user.username,
                 user.email,
                 user.password,
                 authorities
             )
         }
     }

    override fun getAuthorities(): List<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}