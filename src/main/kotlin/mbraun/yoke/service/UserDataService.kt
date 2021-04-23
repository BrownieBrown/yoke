package mbraun.yoke.service

import mbraun.yoke.model.UserData
import mbraun.yoke.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDataService(@Autowired private val userDataRepository: UserDataRepository) {
    fun findUserData(): Sequence<UserData> {
        return userDataRepository.findAll().asSequence()
    }

    fun addUserData(userData: UserData) {
        val mailAddressExist = userDataRepository.selectedEmailExists(userData.email)

//        if (mailAddressExist) {
//            throw Exception(message = "The Email ${userData.email} is already taken")
//        }

        userDataRepository.save(userData)
    }
}