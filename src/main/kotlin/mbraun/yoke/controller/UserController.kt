package mbraun.yoke.controller

import mbraun.yoke.model.Gender
import mbraun.yoke.model.Role
import mbraun.yoke.model.User
import mbraun.yoke.service.UserDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/userData")
class UserController(@Autowired private val userDataService: UserDataService) {

    @GetMapping
    fun getAllUserData(): ResponseEntity<List<User>> {
        return userDataService.getAllUserData()
    }

    @GetMapping("/{id}")
    fun getSingleUserData(@PathVariable id: UUID): ResponseEntity<User> {
        return userDataService.getSingleUserData(id)
    }

    @PostMapping
    fun addUserData(@Valid @RequestBody user: User): ResponseEntity<User> {
        return userDataService.addUserData(user)
    }

    @PatchMapping("/username/{id}")
    fun updateUsername(@PathVariable id: UUID, @RequestBody newUserName: String ): ResponseEntity<User> {
        return userDataService.updateUserName(id, newUserName)
    }

    @PatchMapping("/email/{id}")
    fun updateEmail(@PathVariable id: UUID, @RequestBody newEmail: String ): ResponseEntity<User> {
        return userDataService.updateEmail(id, newEmail)
    }

    @PatchMapping("/age/{id}")
    fun updateAge(@PathVariable id: UUID, @RequestBody newAge: String ): ResponseEntity<User> {
        val ageToInt = newAge.toInt()
        return userDataService.updateAge(id, ageToInt)
    }

    @PatchMapping("/gender/{id}")
    fun updateGender(@PathVariable id: UUID, @RequestBody newGender: Gender ): ResponseEntity<User> {
        return userDataService.updateGender(id, newGender)
    }

    @PatchMapping("/role/{id}")
    fun updateRole(@PathVariable id: UUID, @RequestBody newRole: Role ): ResponseEntity<User> {
        return userDataService.updateRole(id, newRole)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<User> {
        return userDataService.deleteUser(id)
    }

    @DeleteMapping("/delete")
    fun deleteAllData(): ResponseEntity<User>  {
        return userDataService.deleteAllData()
    }
}