package mbraun.yoke.controller

import mbraun.yoke.model.User
import mbraun.yoke.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/user_management/api/v1/userData/")
class UserManagementController(@Autowired val userService: UserService) {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PRIMARY_OWNER')")
    fun getAllUserData(): ResponseEntity<List<User>> {
        return userService.getAllUserData()
    }

    @PostMapping("add_user")
    @PreAuthorize("hasAuthority('new_user:invite')")
    fun addNewUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        return userService.addNewUser(user)
    }

//    @PatchMapping("appoint_admin/{id}")
//    @PreAuthorize("hasAuthority('admins:appoint')")
//    fun appointAdmin(@PathVariable id: UUID ): ResponseEntity<User> {
//        return userService.appointAdmin(id)
//    }
//
//    @PatchMapping("demote_admin/{id}")
//    @PreAuthorize("hasAuthority('admins:demote')")
//    fun demoteAdmin(@PathVariable id: UUID ): ResponseEntity<User> {
//        return userService.demoteAdmin(id)
//    }
//
//    @PatchMapping("appoint_owner/{id}")
//    @PreAuthorize("hasAuthority('owners:appoint')")
//    fun appointOwner(@PathVariable id: UUID ): ResponseEntity<User> {
//        return userService.appointOwner(id)
//    }
//
//    @PatchMapping("demote_owner/{id}")
//    @PreAuthorize("hasAuthority('owners:demote')")
//    fun demoteOwner(@PathVariable id: UUID ): ResponseEntity<User> {
//        return userService.demoteOwner(id)
//    }

    @DeleteMapping("delete_user/{id}")
    @PreAuthorize("hasAuthority('user:remove')")
    fun removeUser(@PathVariable id: UUID): ResponseEntity<User> {
        return userService.removeUser(id)
    }

    @DeleteMapping("delete_all")
    @PreAuthorize("hasRole('PRIMARY_OWNER')")
    fun removeAllUsers(): ResponseEntity<User>  {
        return userService.removeAllUsers()
    }
}