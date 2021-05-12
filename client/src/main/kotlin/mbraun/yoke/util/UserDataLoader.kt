//package mbraun.yoke.util
//
//import mbraun.yoke.model.Role
//import mbraun.yoke.model.User
//import mbraun.yoke.repository.UserRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.stereotype.Component
//import javax.annotation.PostConstruct
//
//@Component
//class UserDataLoader(@Autowired private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
//
//    @PostConstruct
//    fun loadData(){
//        userRepository.deleteAll()
//        userRepository.save(User(
//            "BrownieBrown",
//            "marco.braun2013@icloud.com",
//            passwordEncoder.encode("123456"),
//        ))
//        userRepository.save(User(
//            "SexyJTSophie",
//            "julia.thum@gmx.de",
//            passwordEncoder.encode("123456")
//        ))
//        userRepository.save(User(
//            "DiscoPumper",
//            "yannick.seppich@gmail.com",
//            passwordEncoder.encode("123456"),
//        ))
//    }
//
//}