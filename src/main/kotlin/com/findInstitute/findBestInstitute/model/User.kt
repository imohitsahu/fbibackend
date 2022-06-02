package com.findInstitute.findBestInstitute.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class User(
    @Id
    var id: String?
) {
    var name = ""
    var email = ""
    var password = ""
        @JsonIgnore
        get
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}
