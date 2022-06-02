package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.dto.InstituteDto
import com.findInstitute.findBestInstitute.model.Institute


interface InstituteService {
    fun addInstitute(institute: Institute): Institute
    fun viewAllInstitute(): List<Institute>
    fun viewAllByInstituteCity(city: String): List<Institute>
    fun viewInstituteByEmail(email: String): Institute
    fun deleteInstitute(email: String): String
    fun updateInstitute(email: String, institute: InstituteDto): Institute
    fun changePassword(email: String, oldPassword: String, newPassword: String): Institute
}