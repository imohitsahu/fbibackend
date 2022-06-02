package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.AdminDAO
import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Admin
import org.springframework.stereotype.Service


@Service
class AdminServiceImpl(var adminDAO: AdminDAO) : AdminService {

    override fun addAdmin(email: String, password: String): Admin {
        if (this.adminDAO.existsByEmail(email))
            throw AddException("Already exist")
        else
            return this.adminDAO.save(Admin(email, password))
    }

    override fun viewAdmin(email: String): Admin {
        if (this.adminDAO.existsByEmail(email))
            return this.adminDAO.findById(email).get()
        else
            throw GetException("$email is not an admin")

    }

    override fun deleteAdmin(email: String): String {
        if (this.adminDAO.existsByEmail(email)) {
            this.adminDAO.deleteById(email)
            return "Successfully delete"
        } else
            throw DeleteException("$email is not an admin")
    }

    override fun updateAdmin(email: String, updatedAdmin: Admin): Admin {
        if (adminDAO.existsByEmail(email)) {
            var admin: Admin = this.viewAdmin(email)   //admin not changable so val from suggestion
            admin.email = updatedAdmin.email
            admin.password = updatedAdmin.password
            adminDAO.save(admin)
            return admin
        } else {
            throw GetException("No Admin Found")
        }
    }


    override fun changePassword(email: String, oldPassword: String, newPassword: String): String {
        if (this.adminDAO.existsByEmail(email)) {
            var admin: Admin = this.viewAdmin(email)
            if (admin.password.equals(oldPassword)) {
                admin.password = newPassword
                adminDAO.save(admin)
                return "Successfully change"
            } else
                throw GetException("Invalid old password")
        } else
            throw GetException("No Admin found with this email")
    }
}