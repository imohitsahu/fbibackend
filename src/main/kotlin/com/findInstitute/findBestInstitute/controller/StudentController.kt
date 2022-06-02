package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.dto.StudentDto
import com.findInstitute.findBestInstitute.model.Student
import com.findInstitute.findBestInstitute.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/student")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class StudentController(var studentService: StudentService) {

    @PostMapping
    fun addStudent(@Valid @RequestBody student: Student): ResponseEntity<Student> {
        return ResponseEntity(this.studentService.addStudent(student), HttpStatus.OK)
    }

    @DeleteMapping("/{email}")
    fun deleteStudent(@PathVariable email: String): String = this.studentService.deleteStudent(email)

    @PutMapping("/{email}")
    fun updateStudent(@PathVariable email: String, @Valid @RequestBody student: StudentDto): Student =
        this.studentService.updateStudent(email, student)

    @GetMapping
    fun getStudent(): List<Student> {
        return this.studentService.viewAllStudent()
    }

    @GetMapping("/{email}")
    fun getStudentByEmail(@PathVariable email: String): Student = this.studentService.viewStudentByEmail(email)


    @PutMapping("/{email}/{oldPassword}/{newPassword}")
    fun changeStudentPassword(
        @PathVariable email: String,
        @PathVariable oldPassword: String,
        @PathVariable newPassword: String
    ): Student {
        return this.studentService.changePassword(email, oldPassword, newPassword)
    }
}