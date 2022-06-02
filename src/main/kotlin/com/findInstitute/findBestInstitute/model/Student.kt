package com.findInstitute.findBestInstitute.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Student(
    @Id
    @field:Email(message = "Email should be valid")
    var email: String,
    @field:NotBlank(message = "Name can't be blank, it's mandatory")
    var stuName: String,
    @field: Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,16}\$",
        message = "Should contain at least one character,uppercase,lowercase,special characters,digits and be in 8-16 characters"
    )
    var password: String,
    @field: Pattern(regexp = "^\$|[0-9]{10}", message = "Should be valid phone number")
    var contactNo: String,
    var city: String,
    var state: String,
    @JsonIgnore
    var type: String? = "Student",
    @JsonIgnore
    var isLoggedIn: Boolean? = false,
    var lastLogin: String? = ""
)
