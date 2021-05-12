package mbraun.yoke.security.services

import mbraun.yoke.model.User
import mbraun.yoke.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImplementation(@Autowired private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        
        try {
            val user : User = userRepository.findByUsername(username)
            return UserDetailsImplementation.build(user)
        } catch (e: Exception) {
            throw UsernameNotFoundException("The username $username does not exist")
        }
    }
}