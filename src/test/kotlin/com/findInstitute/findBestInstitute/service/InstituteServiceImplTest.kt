package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Institute
import com.findInstitute.findBestInstitute.dto.InstituteDto
import com.findInstitute.findBestInstitute.exception.UnauthorizedException
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class InstituteServiceImplTest @Autowired constructor(
    var instituteService: InstituteService
){
    @Test
    fun addInstitute(){
        val institute = this.instituteService.addInstitute(Institute("institutetest@gmail.com","Test Institute", "institute@Test$", "9999999999", "Bhopal", "Madhya Pradesh", null,null),)
        assertThat(institute).isNotNull()

        assertThrows<AddException>(message = "Institute already exists"){
            this.instituteService.addInstitute(Institute(institute.email,institute.insName, institute.password,institute.contactNo, institute.city, institute.state, institute.map,))
        }

        assertThat(this.instituteService.viewInstituteByEmail(institute.email)).isNotNull()

        assertThat(this.instituteService.updateInstitute(institute.email, InstituteDto(institute.email,"after Test Institute", "Test@after$", "8888888888", "Surat", "Gujrat", "www.google.com"))).isNotNull()

        assertThat(this.instituteService.deleteInstitute(institute.email)).isEqualTo("Successfully deleted")
    }

    @Test
    fun `should get Not Found for invalid email for find`() {
        var exception = assertThrows<GetException>(message = "No admin found with this email id") {
            this.instituteService.viewInstituteByEmail(("test@gmail.com"))
        }
    }

    @Test
    fun `should get not found for invalid email on updating`(){
        var exception = assertThrows<GetException>(message = "No institute found with this email") {
            this.instituteService.viewInstituteByEmail("test@gmail.com")
        }
    }

    @Test
    fun `should get not found for deleting`(){
        var exception = assertThrows<UnauthorizedException>(message = "No institute found with this email") {
            this.instituteService.deleteInstitute("Test@gmail.com")
        }
    }




}