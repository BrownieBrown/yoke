package mbraun.yoke.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/test")
class TestController {
    @GetMapping("/all")
    fun allAccess(): String {
        return "Public Content."
    }

    @GetMapping("/file_management")
    @PreAuthorize("hasRole('BILLING') or hasRole('VIEWER') or hasRole('CONTRIBUTOR') or hasRole('EDITOR') or hasRole('ADMIN') or hasRole('OWNER') or hasRole('PRIMARY_OWNER')")
    fun fileManagementAccess(): String {
        return "File Management."
    }

    @GetMapping("/account_management")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER') or hasRole('PRIMARY_OWNER')")
    fun accountManagementAccess(): String {
        return "Account Management."
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER') or hasRole('PRIMARY_OWNER')")
    fun userAccess(): String {
        return "User Management."
    }

    @GetMapping("/billing")
    @PreAuthorize("hasRole('BILLING') or hasRole('OWNER') or hasRole('PRIMARY_OWNER')")
    fun billingAccess(): String {
        return "User Management."
    }
}