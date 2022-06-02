package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Institute
import org.springframework.data.mongodb.repository.MongoRepository

interface InstituteDAO : MongoRepository<Institute, String> {
    fun existsByEmail(email: String): Boolean
    fun existsByEmailAndPassword(email: String, password: String): Boolean
    fun findByCity(city: String): List<Institute>
    fun existsByCity(city: String): Boolean

}