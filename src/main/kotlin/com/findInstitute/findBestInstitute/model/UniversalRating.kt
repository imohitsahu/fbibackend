package com.findInstitute.findBestInstitute.model

import org.springframework.data.annotation.Id

data class UniversalRating(
    @Id
    var insEmail: String,
    var uniRating: Long?
)
