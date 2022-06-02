package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Student
import com.findInstitute.findBestInstitute.dto.StudentDto
import com.findInstitute.findBestInstitute.exception.UnauthorizedException
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class StudentServiceImplTest @Autowired constructor(
    var studentService: StudentService
) {
    @Test
    fun addStudent(){
        val student = this.studentService.addStudent(Student("studentTest@gmail.com","Test Student", "Student@Test$", "9999999999", "Bhopal", "Madhya Pradesh"))
        assertThat(student).isNotNull()

        assertThrows<AddException>(message = "Student already exists"){
            this.studentService.addStudent(Student(student.email,student.stuName, student.password,student.contactNo, student.city, student.state))
        }

        assertThat(this.studentService.viewStudentByEmail(student.email)).isNotNull()

        assertThat(this.studentService.updateStudent(student.email, StudentDto(student.email,"after Test Institute", "Test@after$", "8888888888", "Surat", "Gujrat"))).isNotNull()

        assertThat(this.studentService.deleteStudent(student.email)).isEqualTo("Successfully delete")
    }

    @Test
    fun `should get Not Found for invalid email for find`() {
        var exception = assertThrows<GetException>(message = "No admin found with this email id") {
            this.studentService.viewStudentByEmail(("test@gmail.com"))
        }
    }

    @Test
    fun `should get not found for invalid email on updating`(){
        var exception = assertThrows<GetException>(message = "please login") {
            this.studentService.viewStudentByEmail("test@gmail.com")
        }
    }

    @Test
    fun `should get not found for deleting`(){
        var exception = assertThrows<UnauthorizedException>(message = "No institute found with this email") {
            this.studentService.deleteStudent("Test@gmail.com")
        }
    }
}