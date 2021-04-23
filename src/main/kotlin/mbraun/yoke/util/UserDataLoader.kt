//package mbraun.yoke.util
//
//import mbraun.yoke.model.Gender
//import mbraun.yoke.model.Role
//import mbraun.yoke.model.UserData
//import mbraun.yoke.repository.UserDataRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//import java.util.*
//import javax.annotation.PostConstruct
//
//@Component
//class UserDataLoader(@Autowired private val userDataRepository: UserDataRepository) {
//
//    @PostConstruct
//    fun loadData(){
//        userDataRepository.save(UserData(
//            UUID.randomUUID(),
//            "123456",
//            "BrownieBrown",
//            "marco.braun2013@icloud.com",
//            30,
//            Gender.MALE,
//            Role.PRIMARY_OWNER
//        ))
//    }
//
//}