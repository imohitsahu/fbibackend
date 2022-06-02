package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.AdminDAO
import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.DAO.StudentDAO
import com.findInstitute.findBestInstitute.dto.StudentDto
import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.exception.UnauthorizedException
import com.findInstitute.findBestInstitute.model.Student
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    var studentDAO: StudentDAO, var instituteDAO: InstituteDAO, var adminDAO: AdminDAO
) : StudentService {
    override fun addStudent(student: Student): Student {
        if (studentDAO.existsByEmail(student.email) || instituteDAO.existsByEmail(student.email) || adminDAO.existsByEmail(
                student.email
            )
        )
            throw AddException("Email already exists")
        else
            return this.studentDAO.save(student)
    }

    override fun viewAllStudent(): List<Student> = studentDAO.findAll()

    override fun viewStudentByEmail(email: String): Student {
        if (this.studentDAO.existsByEmail(email))
            return this.studentDAO.findById(email).get()
        else
            throw GetException("$email not found")
    }

    override fun deleteStudent(email: String): String {
        if (this.studentDAO.existsByEmail(email)) {
            this.studentDAO.deleteById(email)
            return "Successfully delete"
        } else
            throw UnauthorizedException("please login")
    }

    override fun updateStudent(email: String, student: StudentDto): Student {
        if (studentDAO.existsByEmail(email)) {
            var studentDto = this.viewStudentByEmail(email)
            studentDto.stuName = student.stuName
            studentDto.contactNo = student.contactNo
            studentDto.password = student.password
            studentDto.city = student.city
            studentDto.state = student.state
            return studentDAO.save(studentDto)
        } else throw GetException("Student Not Found")
    }

    override fun changePassword(email: String, oldPassword: String, newPassword: String): Student {
        var student = this.viewStudentByEmail(email)
        if (student.isLoggedIn!!.equals(true)) {
            if (student.password.equals(oldPassword)) {
                student.password = newPassword
                studentDAO.save(student)
                return student
            } else throw GetException("invalid old password")
        } else throw UnauthorizedException("please login")
    }
}