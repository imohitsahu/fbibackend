package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.dto.EnquiryDto
import com.findInstitute.findBestInstitute.model.Enquiry


interface StudentsForEnquiryService {
    fun viewAllEnquiry(): List<Enquiry>
    fun addEnquiry(enquiry: EnquiryDto): Enquiry
    fun viewEnquiryByEmail(insEmail: String): List<Enquiry>
    fun viewEnquiryByStudentEmail(stuEmail: String): List<Enquiry>
    fun deleteEnquiry(insEmail: String, stuEmail: String, courseName: String): String
    fun deleteEnqById(enqId: String): String

}