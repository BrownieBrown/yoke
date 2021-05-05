package mbraun.yoke.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.*
import kotlin.jvm.Transient

@Table(name = "user_data")
@Entity
data class User(
    @Column(name = "id")
    @Id
    private val id: UUID = UUID.randomUUID(),

    @Column(name = "password")
    private var password: String,

    @Column(name = "user_name", unique = true)
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
    val grantedAuthorities:Set<GrantedAuthority>,

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

    override fun getUsername(): String {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (password != other.password) return false
        if (userName != other.userName) return false
        if (email != other.email) return false
        if (age != other.age) return false
        if (gender != other.gender) return false
        if (role != other.role) return false
        if (grantedAuthorities != other.grantedAuthorities) return false
        if (isAccountNonExpired != other.isAccountNonExpired) return false
        if (isAccountNonLocked != other.isAccountNonLocked) return false
        if (isCredentialsNonExpired != other.isCredentialsNonExpired) return false
        if (isEnabled != other.isEnabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + age
        result = 31 * result + gender.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + grantedAuthorities.hashCode()
        result = 31 * result + isAccountNonExpired.hashCode()
        result = 31 * result + isAccountNonLocked.hashCode()
        result = 31 * result + isCredentialsNonExpired.hashCode()
        result = 31 * result + isEnabled.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id=$id, password='$password', userName='$userName', email='$email', age=$age, gender=$gender, role=$role, grantedAuthorities=$grantedAuthorities, isAccountNonExpired=$isAccountNonExpired, isAccountNonLocked=$isAccountNonLocked, isCredentialsNonExpired=$isCredentialsNonExpired, isEnabled=$isEnabled)"
    }


}