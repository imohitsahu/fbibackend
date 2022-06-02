package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.EnquiryDAO
import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.DAO.StudentDAO
import com.findInstitute.findBestInstitute.dto.EnquiryDto
import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetEmptyException
import com.findInstitute.findBestInstitute.model.Enquiry
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class StudentsForEnquiryServiceImpl(
    var studentDAO: StudentDAO,
    var enquiryDAO: EnquiryDAO,
    var instituteDAO: InstituteDAO
) : StudentsForEnquiryService {
    override fun viewAllEnquiry(): List<Enquiry> = enquiryDAO.findAll()

    override fun addEnquiry(enquiryDto: EnquiryDto): Enquiry {
        if (this.studentDAO.existsByEmail(enquiryDto.stuEmail) && this.instituteDAO.existsByEmail(enquiryDto.insEmail)) {
            if (this.enquiryDAO.existsByInsEmailAndStuEmailAndCourseName(
                    enquiryDto.insEmail,
                    enquiryDto.stuEmail,
                    enquiryDto.courseName
                )
            )
                throw AddException("Already raised enquiry")
            else {
                var enquiry = Enquiry()
                enquiry.courseName = enquiryDto.courseName
                enquiry.insEmail = enquiryDto.insEmail
                enquiry.insName = enquiryDto.insName
                enquiry.stuName = enquiryDto.stuName
                enquiry.stuEmail = enquiryDto.stuEmail
                enquiry.phoneNo = enquiryDto.phoneNo
                val now = Date()
                val dtf = SimpleDateFormat("dd-MM-yyyy hh:mm")
                dtf.timeZone=TimeZone.getTimeZone("Asia/Calcutta")
                enquiry.enqTime = dtf.format(now)
                return this.enquiryDAO.save(enquiry)
            }
        } else
            throw AddException("institute or student not found")
    }

    override fun viewEnquiryByEmail(insEmail: String): List<Enquiry> {
        if (enquiryDAO.existsByInsEmail(insEmail))
            return this.enquiryDAO.findByInsEmail(insEmail)
        else
            throw GetEmptyException("No enquiry available")
    }

    override fun viewEnquiryByStudentEmail(stuEmail: String): List<Enquiry> {
        if (enquiryDAO.existsByStuEmail(stuEmail))
            return this.enquiryDAO.findByStuEmail(stuEmail)
        else
            throw GetEmptyException("No enquiry available")
    }


    override fun deleteEnquiry(insEmail: String, stuEmail: String, courseName: String): String {
        if (this.enquiryDAO.existsByInsEmailAndStuEmailAndCourseName(insEmail, stuEmail, courseName)) {
            this.enquiryDAO.deleteById(insEmail)
            return "Delete Successfully"
        } else
            throw DeleteException("Enquiries not found")
    }

    override fun deleteEnqById(enqId: String): String {
        this.enquiryDAO.deleteById(enqId)
        return "Delete Successfully"
    }
}