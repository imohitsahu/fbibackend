package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.AdminDAO
import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.DAO.StudentDAO
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Admin
import com.findInstitute.findBestInstitute.model.Institute
import com.findInstitute.findBestInstitute.model.Student
import com.findInstitute.findBestInstitute.util.JwtUtils
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletResponse

@Service
class LoginServiceImpl(
    var adminDAO: AdminDAO,
    var instituteDAO: InstituteDAO,
    var studentDAO: StudentDAO,
    var adminService: AdminService,
    var instituteService: InstituteService,
    var studentService: StudentService,
    var utils: JwtUtils,
    var sendEmailServiceImpl: SendEmailServiceImpl
) : LoginService {
    override fun login(email: String, password: String, response: HttpServletResponse): Any {
        //  val passwordEncoder = BCryptPasswordEncoder()
        if (adminDAO.existsByEmailAndPassword(email, password)) {
            var admin: Admin = this.adminService.viewAdmin(email)
            admin.isLoggedIn = true
            adminDAO.save(admin)

            val jwt = utils.generateJwt(admin.email, admin.type!!, response)
            var loginList: Any = mutableListOf(admin.type, admin.isLoggedIn, jwt)
            return loginList
        } else if (studentDAO.existsByEmailAndPassword(email, password)) {
            var student: Student = this.studentService.viewStudentByEmail(email)
            student.isLoggedIn = true
            studentDAO.save(student)

            val jwt = utils.generateJwt(student.email, student.type!!, response)
            var loginList: Any = mutableListOf(student.type, student.isLoggedIn, jwt)
            return loginList
        } else if (instituteDAO.existsByEmailAndPassword(email, password)) {
            var institute: Institute = this.instituteService.viewInstituteByEmail(email)
            institute.isLoggedIn = true
            instituteDAO.save(institute)

            val jwt = utils.generateJwt(institute.email, institute.type!!, response)
            var loginList: Any = mutableListOf(institute.type, institute.isLoggedIn, jwt)
            return loginList
        } else
            throw GetException("Email and password Not Found")
    }

    override fun logout(email: String): String {
        if (adminDAO.existsByEmail(email)) {
            var admin: Admin = this.adminService.viewAdmin(email)
            admin.isLoggedIn = false
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val now = LocalDateTime.now()
            admin.lastLogin = dtf.format(now)
            adminDAO.save(admin)
            return "Logout Successfully"
        } else if (studentDAO.existsByEmail(email)) {
            var student: Student = this.studentService.viewStudentByEmail(email)
            student.isLoggedIn = false
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val now = LocalDateTime.now()
            student.lastLogin = dtf.format(now)
            studentDAO.save(student)
            return "Logout Successfully"
        } else if (instituteDAO.existsByEmail(email)) {
            var institute: Institute = this.instituteService.viewInstituteByEmail(email)
            institute.isLoggedIn = false
            val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val now = LocalDateTime.now()
            institute.lastLogin = dtf.format(now)
            instituteDAO.save(institute)
            return "Logout Successfully"
        } else
            throw GetException("Invalid email")
    }

    override fun forgetPassword(email: String): String {
        if (adminDAO.existsByEmail(email)) {
            var admin: Admin = this.adminService.viewAdmin(email)
            var subject: String = "Welcome to the journey of Finding Best Institute"
            var body: String =
                "Hi Admin,\n Your request for forget password has been received and change your password by after login.  \nYour password details are: \nEmail: ${admin.email}\nPassword: ${admin.password}.\n\n Thanks,\n Find Best Institute \n ****This is a system generated communication does not require signature. ****"
            sendEmailServiceImpl.sendMail(admin.email, body, subject)
            return "Password successfully sent to your registered email."
        } else if (studentDAO.existsByEmail(email)) {
            var student: Student = this.studentService.viewStudentByEmail(email)
            var subject: String = "Welcome to the journey of Finding Best Institute"
            var body: String =
                "Hi ${student.stuName},\n Your request for forget password has been received and change your password by after login.  \nYour password details are: \nName: ${student.stuName}\nEmail: ${student.email}\nPassword: ${student.password}.\n\n Thanks,\n Find Best Institute \n ****This is a system generated communication does not require signature. ****"
            sendEmailServiceImpl.sendMail(student.email, body, subject)
            return "Password successfully sent to your registered email."
        } else if (instituteDAO.existsByEmail(email)) {
            var institute = this.instituteService.viewInstituteByEmail(email)
            var subject: String = "Welcome to the journey of Finding Best Institute"
            var body: String =
                "Hi ${institute.insName},\n Your request for forget password has been received and change your password by after login.  \nYour password details are: \nName: ${institute.insName}\nEmail: ${institute.email}\nPassword: ${institute.password}.\n\n Thanks,\n Find Best Institute \n ****This is a system generated communication does not require signature. ****"
            sendEmailServiceImpl.sendMail(institute.email, body, subject)
            return "Password successfully sent to your registered email."

        } else {
            throw GetException("Invalid Email")
        }
    }

}