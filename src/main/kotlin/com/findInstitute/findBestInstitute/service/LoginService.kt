package com.findInstitute.findBestInstitute.service

import javax.servlet.http.HttpServletResponse

interface LoginService {
    fun login(email: String, password: String, response: HttpServletResponse): Any
    fun logout(email: String): String
    fun forgetPassword(email: String): String
}