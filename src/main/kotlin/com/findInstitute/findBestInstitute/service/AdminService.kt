package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.model.Admin

interface AdminService {
    fun addAdmin(email: String, password: String): Admin
    fun viewAdmin(email: String): Admin
    fun deleteAdmin(email: String): String
    fun updateAdmin(email: String, admin: Admin): Admin
    fun changePassword(email: String, oldPassword: String, newPassword: String): String
}