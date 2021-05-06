package mbraun.yoke.controller

import mbraun.yoke.model.Gender
import mbraun.yoke.model.Role
import mbraun.yoke.model.User
import mbraun.yoke.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/userData")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping
    fun getAllUserData(): ResponseEntity<List<User>> {
        return userService.getAllUserData()
    }

    @GetMapping("/{id}")
    fun getSingleUserData(@PathVariable id: UUID): ResponseEntity<User> {
        return userService.getSingleUserData(id)
    }

    @PatchMapping("/username/{id}")
    fun updateUsername(@PathVariable id: UUID, @RequestBody newUserName: String ): ResponseEntity<User> {
        return userService.updateUserName(id, newUserName)
    }

    @PatchMapping("/email/{id}")
    fun updateEmail(@PathVariable id: UUID, @RequestBody newEmail: String ): ResponseEntity<User> {
        return userService.updateEmail(id, newEmail)
    }

    @PatchMapping("/age/{id}")
    fun updateAge(@PathVariable id: UUID, @RequestBody newAge: String ): ResponseEntity<User> {
        val ageToInt = newAge.toInt()
        return userService.updateAge(id, ageToInt)
    }

    @PatchMapping("/gender/{id}")
    fun updateGender(@PathVariable id: UUID, @RequestBody newGender: Gender ): ResponseEntity<User> {
        return userService.updateGender(id, newGender)
    }

    @PatchMapping("/role/{id}")
    fun updateRole(@PathVariable id: UUID, @RequestBody newERole: Role ): ResponseEntity<User> {
        return userService.updateRole(id, newERole)
    }
}