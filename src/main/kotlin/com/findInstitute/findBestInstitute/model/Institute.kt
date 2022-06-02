package com.findInstitute.findBestInstitute.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.executable.ValidateOnExecution

@Document
@ValidateOnExecution
data class Institute(
    @Id
    @field:Email(message = "Email should be valid")
    var email: String,
    @field:NotBlank(message = "Name can't be blank, it's mandatory")
    var insName: String,
    @field: Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,16}\$",
        message = "Should contain atleast one character,uppercase,lowercase,special characters,digits and be in 8-16 charcters"
    )
    var password: String,
    @field: Pattern(regexp = "^\$|[0-9]{10}", message = "Should be valid phone number")
    var contactNo: String,
    var city: String,
    var state: String,
    var map: String?,
    @JsonIgnore
    var type: String? = "Institute",
    @JsonIgnore
    var isLoggedIn: Boolean? = false,
    var lastLogin: String? = ""

) {
    constructor(
        email: String,
        insName: String,
        password: String,
        contactNo: String,
        city: String,
        state: String,
        map: String?
    ) : this(email, insName, password, contactNo, city, state, map, null)
}