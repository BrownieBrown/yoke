package mbraun.yoke.controller

import mbraun.yoke.model.Role
import mbraun.yoke.model.User
import mbraun.yoke.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/management/api/v1/userData/")
class UserManagementController(@Autowired private val userService: UserService) {

    @PostMapping("add_user")
    fun addNewUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        return userService.addNewUser(user)
    }

    @PatchMapping("appoint_admin/{id}")
    fun appointAdmin(@PathVariable id: UUID ): ResponseEntity<User> {
        return userService.appointAdmin(id)
    }

    @PatchMapping("demote_admin/{id}")
    fun demoteAdmin(@PathVariable id: UUID ): ResponseEntity<User> {
        return userService.demoteAdmin(id)
    }

    @DeleteMapping("delete_user/{id}")
    fun removeUser(@PathVariable id: UUID): ResponseEntity<User> {
        return userService.removeUser(id)
    }

    @DeleteMapping("delete_all")
    fun removeAllUsers(): ResponseEntity<User>  {
        return userService.removeAllUsers()
    }
}