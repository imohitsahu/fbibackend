package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Admin
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminServiceImplTest @Autowired constructor(
    var adminService: AdminService
){
    @Test
    fun addAdmin(){

        val admin = this.adminService.addAdmin("kumar4Test@gmail.com", "Mohit$123")
        assertThat(admin).isNotNull()

        assertThrows<AddException>(message = "Admin already exists"){
            this.adminService.addAdmin(admin.email, admin.password)
        }

        assertThat(this.adminService.viewAdmin(admin.email)).isNotNull()

        assertThat(this.adminService.updateAdmin(admin.email,Admin(admin.email,"mm4Test%123"))).isNotNull()

        assertThat(this.adminService.deleteAdmin(admin.email)).isEqualTo("Successfully delete")
    }

    @Test
    fun `should get Not Found for invalid email for find`(){
        var exception = assertThrows<GetException>(message = "Test@gmail.com is not an admin") {
            this.adminService.viewAdmin("Test@gmail.com")
        }

    }

    @Test
    fun `should get not found for invalid email on updating`(){
        var exception = assertThrows<GetException>(message = "No Admin Found") {
            this.adminService.updateAdmin("Test@gmail.com",Admin("Test@gmail.com","asdf"))
        }
    }

    @Test
    fun `should get not found for deleting`(){
        var exception = assertThrows<DeleteException>(message = "No admin found with this email") {
            this.adminService.deleteAdmin("Test@gmail.com")
        }
    }
}