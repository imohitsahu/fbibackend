package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.dto.LoginDto
import com.findInstitute.findBestInstitute.dto.LogoutDto
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Admin
import com.findInstitute.findBestInstitute.model.Institute
import com.findInstitute.findBestInstitute.model.Student
import com.findInstitute.findBestInstitute.service.AdminService
import com.findInstitute.findBestInstitute.service.InstituteService
import com.findInstitute.findBestInstitute.service.LoginService
import com.findInstitute.findBestInstitute.service.StudentService
import com.findInstitute.findBestInstitute.util.JwtUtils
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin(origins = ["https://find-best-institute.herokuapp.com/"], allowCredentials = "true")
class LoginController(
    var loginService: LoginService,
    var util: JwtUtils,
    var adminService: AdminService,
    var studentService: StudentService,
    var instituteService: InstituteService
) {

    private val jwtNull = "Email and password Not Found"
    private val invalidType = "Invalid type"

    @PutMapping("/login")
    fun login(@RequestBody loginDto: LoginDto, response: HttpServletResponse): Any {
        return this.loginService.login(loginDto.email, loginDto.password, response)
    }

    @GetMapping("/responseadmin")
    fun admin(@CookieValue("Admin") jwt: String?): Admin {
        try {
            if (jwt == null) {
                throw GetException(jwtNull)
            }
            val body = util.verify(jwt)
            if (body["type"]?.equals("Admin") == true) {
                return this.adminService.viewAdmin(body.issuer.toString())
            } else {
                throw GetException(invalidType)
            }
        } catch (e: Exception) {
            throw GetException("unauthenticated")
        }
    }

    @GetMapping("/responsestudent")
    fun student(@CookieValue("Student") jwt: String?): Student {
        try {
            if (jwt == null) {
                throw GetException(jwtNull)
            }
            val body = util.verify(jwt)
            if (body["type"]?.equals("Student") == true) {
                return this.studentService.viewStudentByEmail(body.issuer)
            } else {
                throw GetException(invalidType)
            }
        } catch (e: Exception) {
            throw GetException("unauthenticated")
        }
    }

    @GetMapping("/responseinstitute")
    fun institute(@CookieValue("Institute") jwt: String?): Institute {
        try {
            if (jwt == null) {
                throw GetException(jwtNull)
            }
            val body = util.verify(jwt)
            if (body["type"]?.equals("Institute") == true) {
                return this.instituteService.viewInstituteByEmail(body.issuer)
            } else {
                throw GetException(invalidType)
            }
        } catch (e: Exception) {
            throw GetException("unauthenticated")
        }
    }

    @PutMapping("/logout")
    fun logout(@RequestBody logoutDto: LogoutDto, response: HttpServletResponse): String {
        val cookie = Cookie(logoutDto.type, "")
        cookie.maxAge = 0
        response.addCookie(cookie)

        return this.loginService.logout(logoutDto.email)
    }

    @GetMapping("/{email}")
    fun forgetPassword(@PathVariable email: String): String {
        return this.loginService.forgetPassword(email)
    }
}