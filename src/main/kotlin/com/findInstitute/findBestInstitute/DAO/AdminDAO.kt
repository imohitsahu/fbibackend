package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Admin
import org.springframework.data.mongodb.repository.MongoRepository

interface AdminDAO : MongoRepository<Admin, String> {
    fun existsByEmail(email: String): Boolean
    fun existsByEmailAndPassword(email: String, password: String): Boolean
}