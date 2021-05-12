package mbraun.yoke.repository

import mbraun.yoke.model.ERole
import mbraun.yoke.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByName(name: ERole): Role
}