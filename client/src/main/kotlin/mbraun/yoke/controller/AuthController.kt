package mbraun.yoke.controller

import mbraun.yoke.model.User
import mbraun.yoke.payload.request.LoginRequest
import mbraun.yoke.payload.request.SignupRequest
import mbraun.yoke.payload.response.JwtResponse
import mbraun.yoke.payload.response.MessageResponse
import mbraun.yoke.repository.RoleRepository
import mbraun.yoke.repository.UserRepository
import mbraun.yoke.security.jwt.JwtUtils
import mbraun.yoke.security.services.UserDetailsImplementation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid


@CrossOrigin(origins = ["*"], maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
class AuthController(
    @Autowired val authenticationManager: AuthenticationManager,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val roleRepository: RoleRepository,
    @Autowired private val encoder: PasswordEncoder,
    @Autowired private val jwtUtils: JwtUtils
) {


    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<JwtResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)

        val userDetails: UserDetailsImplementation = authentication.principal as UserDetailsImplementation

        val roles = userDetails.authorities.map { it.authority }.toList()

        return ResponseEntity.ok(JwtResponse(
            jwt,
            userDetails.id,
            userDetails.username,
            userDetails.email,
            roles
        ))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signupRequest: SignupRequest): ResponseEntity<MessageResponse> {
        val username = signupRequest.username
        val email = signupRequest.email

        if (userRepository.selectedUserNameExists(username)) {
            return ResponseEntity.badRequest().body(MessageResponse("Error: Username $username is already in use."))
        }

        if (userRepository.selectedEmailExists(email)) {
            return ResponseEntity.badRequest().body(MessageResponse("Error: Email $email is already in use."))
        }

        val password = signupRequest.password
        val eRole = signupRequest.role
        val role = setOf(roleRepository.findByName(eRole))
        val user = User(username, email, encoder.encode(password), role)

        userRepository.save(user)

        return ResponseEntity.ok(MessageResponse("User registered successfully"))
    }


}

