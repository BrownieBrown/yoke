package mbraun.yoke.service

import mbraun.yoke.exception.ResourceNotFoundException
import mbraun.yoke.model.Gender
import mbraun.yoke.model.Role
import mbraun.yoke.model.UserData
import mbraun.yoke.repository.UserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserDataService(@Autowired private val userDataRepository: UserDataRepository) {

    fun searchForUser(id: UUID): UserData {
        return userDataRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("The user with id = $id does not exist.") }
    }

    fun getAllUserData(): ResponseEntity<List<UserData>> {
        val userData = userDataRepository.findAll()

        if (userData.isEmpty()) {
            return ResponseEntity<List<UserData>>(NO_CONTENT)
        }

        return ResponseEntity<List<UserData>>(OK)
    }


    fun getSingleUserData(id: UUID): ResponseEntity<UserData> {
        val user = searchForUser(id)
        return ResponseEntity<UserData>(user, OK)

    }

    fun addUserData(userData: UserData): ResponseEntity<UserData> {
        val mailAddressExists = userDataRepository.selectedEmailExists(userData.email)
        val userNameExists = userDataRepository.selectedUserNameExists(userData.userName)

        if (mailAddressExists) {
            throw Exception("The email ${userData.email} is already taken.")
        }

        if (userNameExists) {
            throw Exception("The user name ${userData.userName} is already taken.")
        }

        return ResponseEntity<UserData>(userDataRepository.save(userData), CREATED)
    }

    fun updateUserName(id: UUID, newUserName: String): ResponseEntity<UserData> {
        val user = searchForUser(id)
        val userNameExists = userDataRepository.selectedUserNameExists(newUserName)

        if (userNameExists) {
            throw Exception("The user name $newUserName is already taken.")
        }

        user.userName = newUserName

        return ResponseEntity<UserData>(userDataRepository.save(user), OK)

    }

    fun updateEmail(id: UUID, newEmail: String): ResponseEntity<UserData> {
        val user = searchForUser(id)
        val mailAddressExists = userDataRepository.selectedEmailExists(newEmail)

        if (mailAddressExists) {
            throw Exception("The email $newEmail is already taken.")
        }

        user.email = newEmail

        return ResponseEntity<UserData>(userDataRepository.save(user), OK)
    }

    fun updateAge(id: UUID, newAge: Int): ResponseEntity<UserData> {
        val user = searchForUser(id)

        if (newAge < 18 || newAge > 150) {
            throw Exception("You must be over 18 or under 150 years old.")
        }

        user.age = newAge

        return ResponseEntity<UserData>(userDataRepository.save(user), OK)
    }

    fun updateGender(id: UUID, newGender: Gender): ResponseEntity<UserData> {
        val user = searchForUser(id)
        user.gender = newGender

        return ResponseEntity<UserData>(userDataRepository.save(user), OK)
    }

    fun updateRole(id: UUID, newRole: Role): ResponseEntity<UserData> {
        val user = searchForUser(id)
        user.role = newRole

        return ResponseEntity<UserData>(userDataRepository.save(user), OK)
    }

    fun deleteUser(id: UUID): ResponseEntity<UserData> {
        searchForUser(id)
        userDataRepository.deleteById(id)

        return ResponseEntity<UserData>(NO_CONTENT)
    }

    fun deleteAllData(): ResponseEntity<UserData> {
        userDataRepository.deleteAll()

        return ResponseEntity<UserData>(NO_CONTENT)
    }


}