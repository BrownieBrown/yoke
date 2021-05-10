package mbraun.yoke.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class AuthController {

    @GetMapping("login")
    fun getLoginView(): String {
        return "login"
    }

    @GetMapping ("user")
    fun getUsers(): String {
        return "user"
    }
}