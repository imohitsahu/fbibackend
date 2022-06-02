package com.findInstitute.findBestInstitute.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class Course(
    @Id
    var courseId: String? = ObjectId().toHexString(),
    var insEmail: String,
    var courseName: String,
    var courseFee: String
)