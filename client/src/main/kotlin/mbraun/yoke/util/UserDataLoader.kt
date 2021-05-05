package mbraun.yoke.util

import mbraun.yoke.model.EGender.FEMALE
import mbraun.yoke.model.EGender.MALE
import mbraun.yoke.model.ERole.*
import mbraun.yoke.model.User
import mbraun.yoke.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class UserDataLoader(@Autowired private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    @PostConstruct
    fun loadData(){
        userRepository.deleteAll()
        userRepository.save(User(
            UUID.fromString("129412fb-cc37-45f4-849c-e649a32e3e2a"),
            passwordEncoder.encode("123456"),
            "BrownieBrown",
            "marco.braun2013@icloud.com",
            30,
            MALE,
            PRIMARY_OWNER,
            PRIMARY_OWNER.getGrantedAuthorities()
        ))
        userRepository.save(User(
            UUID.fromString("893922c8-93b4-4356-b261-69cd18ba9e23"),
            passwordEncoder.encode("123456"),
            "SexyJTSophie",
            "julia.thum@gmx.de",
            25,
            FEMALE,
            VIEWER,
            VIEWER.getGrantedAuthorities()
        ))
        userRepository.save(User(
            UUID.fromString("aae7e617-bc9e-4aff-8ad0-babf2b901a6b"),
            passwordEncoder.encode("123456"),
            "Disco Pumper",
            "yannick.seppich@gmail.com",
            26,
            MALE,
            OWNER,
            OWNER.getGrantedAuthorities()
        ))
    }
}