package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.dto.EnquiryDto
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetEmptyException
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentsForEnquiryServiceImplTest @Autowired constructor(
    var enquiryService: StudentsForEnquiryService
) {
    @Test
    fun addEnquiry(){
        val enquiry = this.enquiryService.addEnquiry(EnquiryDto("java","manipal@manipal.com","manipal","Harsh k.","harish@gmail.com", "9898989898",null ))
        assertThat(enquiry).isNotNull()

        assertThat(this.enquiryService.viewEnquiryByEmail(enquiry.insEmail!!)).isNotNull()

        assertThat(this.enquiryService.deleteEnqById(enquiry.enqId!!)).isEqualTo("Delete Successfully")
    }

    @Test
    fun `should get Not Found for invalid email for find`() {
        var exception = assertThrows<GetEmptyException>(message = "No enquiry found with this email id") {
            this.enquiryService.viewEnquiryByEmail("test@gmail.com")
        }
    }

    @Test
    fun `should get not found for deleting`(){
        var exception = assertThrows<DeleteException>(message = "No institute found with this email") {
            this.enquiryService.deleteEnquiry("Test@gmail.com", "stuTest@gmail.com", "Python")
        }
    }
}