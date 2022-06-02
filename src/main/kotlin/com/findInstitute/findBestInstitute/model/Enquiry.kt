package com.findInstitute.findBestInstitute.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.time.format.DateTimeFormatter

data class Enquiry(
    @Id
    var enqId: String? = ObjectId().toHexString(),
    var courseName: String?,
    var insEmail: String?,
    var insName: String?,
    var stuName: String?,
    var stuEmail: String?,
    var phoneNo: String?,
    var enqTime: String?
) {
    constructor() : this(null, null, null, null, null, null, null, null)
}