package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.dto.EnquiryDto
import com.findInstitute.findBestInstitute.model.Enquiry
import com.findInstitute.findBestInstitute.service.StudentsForEnquiryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/enquiry")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class EnquiryController(var studentsForEnquiryService: StudentsForEnquiryService) {

    @GetMapping
    fun getEnquiries(): List<Enquiry> {
        return this.studentsForEnquiryService.viewAllEnquiry()
    }

    @PostMapping
    fun addEnquiry(@RequestBody enquiry: EnquiryDto): ResponseEntity<Enquiry> {
        return ResponseEntity(this.studentsForEnquiryService.addEnquiry(enquiry), HttpStatus.OK)
    }

    @GetMapping("/insenq/{insEmail}")
    fun getEnquiryByInsEmail(@PathVariable insEmail: String): List<Enquiry> {
        return this.studentsForEnquiryService.viewEnquiryByEmail(insEmail)
    }

    @GetMapping("/stuenq/{stuEmail}")
    fun getEnquiryByStuEmail(@PathVariable stuEmail: String): List<Enquiry> {
        return this.studentsForEnquiryService.viewEnquiryByStudentEmail(stuEmail)
    }

    @DeleteMapping("/{insEmail}/{stuEmail}/{courseName}")
    fun deleteEnquiry(
        @PathVariable insEmail: String,
        @PathVariable stuEmail: String,
        @PathVariable courseName: String
    ): String = this.studentsForEnquiryService.deleteEnquiry(insEmail, stuEmail, courseName)

    @DeleteMapping("/{enqId}")
    fun deleteEnqById(@PathVariable enqId: String): String = this.studentsForEnquiryService.deleteEnqById(enqId)
}