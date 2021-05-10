package mbraun.yoke.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.*
import kotlin.jvm.Transient

@Table(name = "users")
@Entity
data class User(
    @Id
    private val id: UUID = UUID.randomUUID(),

    @Column(name = "password")
    private var password: String,

    @Column(name = "username", unique = true)
    @NotNull
    @NotBlank(message =  "UserName can not be null")
    var userName: String,

    @Column(name = "email", unique = true)
    @Email
    @NotNull
    @NotEmpty
    var email: String,

    @Column(name = "age")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    var age: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    var gender: Gender,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: Role,

    @Transient
    val grantedAuthorities: Set<GrantedAuthority>,

    @Column(name = "account_non_expired")
   private val isAccountNonExpired: Boolean = true,

    @Column(name = "account_non_locked")
    private val isAccountNonLocked: Boolean = true,

    @Column(name = "credentials_non_expired")
    private val isCredentialsNonExpired: Boolean = true,

    @Column(name = "is_enabled")
    private val isEnabled: Boolean = true


) : UserDetails {

    override fun getAuthorities(): Set<GrantedAuthority> {
        return grantedAuthorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {authorities
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return isEnabled
    }
}