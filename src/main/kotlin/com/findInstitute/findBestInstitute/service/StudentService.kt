package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.dto.StudentDto
import com.findInstitute.findBestInstitute.model.Student

interface StudentService {

    fun addStudent(student: Student): Student
    fun viewAllStudent(): List<Student>
    fun viewStudentByEmail(email: String): Student
    fun deleteStudent(email: String): String
    fun updateStudent(email: String, student: StudentDto): Student
    fun changePassword(email: String, oldPassword: String, newPassword: String): Student

}