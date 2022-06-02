package com.findInstitute.findBestInstitute.model

import org.bson.types.ObjectId

data class Rating(
    var ratingId: String? = ObjectId().toHexString(),
    var insEmail: String,
    var stuEmail: String,
    var rate: Long,
    var feedBack: String
)
