package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.AdminDAO
import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.DAO.StudentDAO
import com.findInstitute.findBestInstitute.dto.InstituteDto
import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.GetEmptyException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.exception.UnauthorizedException
import com.findInstitute.findBestInstitute.model.Institute
import org.springframework.stereotype.Service

@Service
class InstituteServiceImpl(
    var instituteDAO: InstituteDAO,
    var studentDAO: StudentDAO,
    var adminDAO: AdminDAO
) : InstituteService {

    override fun addInstitute(institute: Institute): Institute {
        if (instituteDAO.existsByEmail(institute.email) || studentDAO.existsByEmail(institute.email) || adminDAO.existsByEmail(
                institute.email
            )
        )
            throw AddException("Already Registered")
        else
            return this.instituteDAO.save(institute)
    }

    override fun viewAllInstitute(): List<Institute> {
        if (instituteDAO.count() > 0)
            return this.instituteDAO.findAll()
        else
            throw GetEmptyException("No Institute found")
    }

    override fun viewAllByInstituteCity(city: String): List<Institute> {
        if (instituteDAO.existsByCity(city))
            return this.instituteDAO.findByCity(city)
        else
            throw GetEmptyException("No City found")
    }

    override fun viewInstituteByEmail(email: String): Institute {
        if (this.instituteDAO.existsByEmail(email)) {
            return this.instituteDAO.findById(email).get()
        } else
            throw GetException("$email does not exist")
    }

    override fun deleteInstitute(email: String): String {
        if (this.instituteDAO.existsByEmail(email)) {
            this.instituteDAO.deleteById(email)
            return "Successfully deleted"
        } else
            throw UnauthorizedException("Please login")
    }

    override fun updateInstitute(email: String, institute: InstituteDto): Institute {
        if (instituteDAO.existsByEmail(email)) {
            var instituteDto = this.viewInstituteByEmail(email)
            instituteDto.email = institute.email
            instituteDto.insName = institute.insName
            instituteDto.contactNo = institute.contactNo
            instituteDto.password = institute.password
            instituteDto.city = institute.city
            instituteDto.state = institute.state
            instituteDto.map = institute.map
            return instituteDAO.save(instituteDto)
        } else throw GetException("Institute not found")
    }

    override fun changePassword(email: String, oldPassword: String, newPassword: String): Institute {
        var institute = this.viewInstituteByEmail(email)
        if (institute.isLoggedIn!!) {
            if (institute.password == oldPassword) {
                institute.password = newPassword
                instituteDAO.save(institute)
                return institute
            } else throw GetException("invalid old password")
        } else throw UnauthorizedException("please login")
    }
}