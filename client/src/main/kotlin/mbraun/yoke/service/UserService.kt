package mbraun.yoke.service

import mbraun.yoke.exception.ResourceNotFoundException
import mbraun.yoke.model.User
import mbraun.yoke.repository.UserRepository
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(private val userRepository: UserRepository) {

    fun searchForUser(id: UUID): User {
        return userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("The user with id = $id does not exist.") }
    }

    fun getAllUserData(): ResponseEntity<List<User>> {
        val userData = userRepository.findAll()

        if (userData.isEmpty()) {
            return ResponseEntity<List<User>>(NO_CONTENT)
        }

        return ResponseEntity<List<User>>(userData, OK)
    }


    fun getSingleUserData(id: UUID): ResponseEntity<User> {
        val user = searchForUser(id)
        return ResponseEntity<User>(user, OK)

    }

    fun addNewUser(user: User): ResponseEntity<User> {
        val mailAddressExists = userRepository.selectedEmailExists(user.email)
        val userNameExists = userRepository.selectedUserNameExists(user.username)

        if (mailAddressExists) {
            throw Exception("The email ${user.email} is already taken.")
        }

        if (userNameExists) {
            throw Exception("The user name ${user.username} is already taken.")
        }

        return ResponseEntity<User>(userRepository.save(user), CREATED)
    }

    fun updateUserName(id: UUID, newUserName: String): ResponseEntity<User> {
        val user = searchForUser(id)
        val userNameExists = userRepository.selectedUserNameExists(newUserName)

        if (userNameExists) {
            throw Exception("The user name $newUserName is already taken.")
        }

        user.username = newUserName

        return ResponseEntity<User>(userRepository.save(user), OK)

    }

    fun updateEmail(id: UUID, newEmail: String): ResponseEntity<User> {
        val user = searchForUser(id)
        val mailAddressExists = userRepository.selectedEmailExists(newEmail)

        if (mailAddressExists) {
            throw Exception("The email $newEmail is already taken.")
        }

        user.email = newEmail

        return ResponseEntity<User>(userRepository.save(user), OK)
    }

//    fun updateAge(id: UUID, newAge: Int): ResponseEntity<User> {
//        val user = searchForUser(id)
//
//        if (newAge < 18 || newAge > 150) {
//            throw Exception("You must be over 18 or under 150 years old.")
//        }
//
//        user.age = newAge
//
//        return ResponseEntity<User>(userRepository.save(user), OK)
//    }

//    fun updateGender(id: UUID, newGender: EGender): ResponseEntity<User> {
//        val user = searchForUser(id)
//        user.gender = newGender
//
//        return ResponseEntity<User>(userRepository.save(user), OK)
//    }


    fun removeUser(id: UUID): ResponseEntity<User> {
        searchForUser(id)
        userRepository.deleteById(id)

        return ResponseEntity<User>(NO_CONTENT)
    }

    fun removeAllUsers(): ResponseEntity<User> {
        userRepository.deleteAll()

        return ResponseEntity<User>(NO_CONTENT)
    }

}