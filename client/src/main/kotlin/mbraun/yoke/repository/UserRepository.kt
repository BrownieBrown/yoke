package mbraun.yoke.repository

import mbraun.yoke.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository("yoke")
interface UserRepository: JpaRepository<User, UUID> {

    @Query("SELECT case when count(s) > 0 then true else false end from user_data s where s.email = ?1", nativeQuery = true)
    fun selectedEmailExists(email: String): Boolean

    @Query("SELECT case when count(s) > 0 then true else false end from user_data s where s.user_name = ?1", nativeQuery = true)
    fun selectedUserNameExists(userName: String) : Boolean

    fun findByUsername(username: String): User

}