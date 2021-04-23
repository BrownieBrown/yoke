package mbraun.yoke.controller

import mbraun.yoke.model.UserData
import mbraun.yoke.service.UserDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/userData")
class UserDataController(@Autowired private val userDataService: UserDataService) {

    @GetMapping
    fun getAllUserData(): ResponseEntity<Sequence<UserData>> {
        val userData = userDataService.findUserData()
        return ResponseEntity.ok().body(userData)
    }

    @PostMapping
    fun addUserData(@Valid @RequestBody userData: UserData) {
        userDataService.addUserData(userData)
    }
}