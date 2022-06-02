package com.findInstitute.findBestInstitute.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class Admin(
    @Id
    @field:Email(message = "Email should be valid")
    var email: String,
    @field: Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,16}\$",
        message = "Should contain atleast one character,uppercase,lowercase,special characters,digits and be in 8-16 charcters"
    )
    var password: String,
    @JsonIgnore
    var type: String? = "Admin",
    @JsonIgnore
    var isLoggedIn: Boolean? = false,
    var lastLogin: String? = ""
)
