package mbraun.yoke.util

import mbraun.yoke.model.Gender
import mbraun.yoke.model.Role
import mbraun.yoke.model.UserData
import mbraun.yoke.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class UserDataLoader(@Autowired private val userDataRepository: UserDataRepository) {

    @PostConstruct
    fun loadData(){
        userDataRepository.deleteAll()
        userDataRepository.save(UserData(
            UUID.fromString("129412fb-cc37-45f4-849c-e649a32e3e2a"),
            "123456",
            "BrownieBrown",
            "marco.braun2013@icloud.com",
            30,
            Gender.MALE,
            Role.PRIMARY_OWNER
        ))
        userDataRepository.save(UserData(
            UUID.fromString("893922c8-93b4-4356-b261-69cd18ba9e23"),
            "i_love_marco",
            "SexyJTSophie",
            "julia.thum@gmx.de",
            25,
            Gender.FEMALE,
            Role.ADMIN
        ))
    }

}