package mbraun.yoke.util

import mbraun.yoke.model.Gender.FEMALE
import mbraun.yoke.model.Gender.MALE
import mbraun.yoke.model.Role.*
import mbraun.yoke.model.User
import mbraun.yoke.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class UserDataLoader(@Autowired private val userRepository: UserRepository) {

    @PostConstruct
    fun loadData(){
        userRepository.deleteAll()
        userRepository.save(User(
            UUID.fromString("129412fb-cc37-45f4-849c-e649a32e3e2a"),
            "123456",
            "BrownieBrown",
            "marco.braun2013@icloud.com",
            30,
            MALE,
            PRIMARY_OWNER
        ))
        userRepository.save(User(
            UUID.fromString("893922c8-93b4-4356-b261-69cd18ba9e23"),
            "i_love_marco",
            "SexyJTSophie",
            "julia.thum@gmx.de",
            25,
            FEMALE,
            VIEWER
        ))
        userRepository.save(User(
            UUID.fromString("aae7e617-bc9e-4aff-8ad0-babf2b901a6b"),
            "ProteinShake",
            "Disco Pumper",
            "yannick.seppich@gmail.com",
            26,
            MALE,
            OWNER
        ))
    }
}