package mbraun.yoke.service

import mbraun.yoke.model.UserData
import mbraun.yoke.repository.UserDataRepository
import org.apache.catalina.util.ResourceSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDataService(@Autowired private val userDataRepository: UserDataRepository) {
    fun findUserData(): Sequence<UserData> {
        return userDataRepository.findAll().asSequence()
    }

    fun getAllUserData(): ResponseEntity<List<UserData>> {
        val userData = userDataRepository.findAll().toList()

        if (userData.isEmpty()) {
            return ResponseEntity<List<UserData>>(NO_CONTENT)
        }

        return ResponseEntity<List<UserData>>(OK)
    }

    fun getSingleUserData(id: UUID) {
//        val singleUserData = userDataRepository.findById(id)

    }

    fun addUserData(userData: UserData) {
        val mailAddressExist = userDataRepository.selectedEmailExists(userData.email)

        if (mailAddressExist) {
            throw Exception("The Email ${userData.email} is already taken")
        }

        userDataRepository.save(userData)
    }

}