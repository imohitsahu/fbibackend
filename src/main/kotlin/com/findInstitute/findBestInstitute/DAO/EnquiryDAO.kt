package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Enquiry
import org.springframework.data.mongodb.repository.MongoRepository

interface EnquiryDAO : MongoRepository<Enquiry, String> {
    fun existsByInsEmail(insEmail: String): Boolean
    fun existsByStuEmail(stuEmail: String): Boolean
    fun findByInsEmail(insEmail: String): List<Enquiry>
    fun findByStuEmail(stuEmail: String): List<Enquiry>
    fun existsByInsEmailAndStuEmailAndCourseName(insEmail: String, stuEmail: String, courseName: String): Boolean


}