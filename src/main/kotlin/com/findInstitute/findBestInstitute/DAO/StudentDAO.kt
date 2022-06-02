package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Student
import org.springframework.data.mongodb.repository.MongoRepository

interface StudentDAO : MongoRepository<Student, String> {
    fun existsByEmail(email: String): Boolean
    fun existsByEmailAndPassword(email: String, password: String): Boolean
    //fun existsByContactNo(contactNo : String) : Boolean

}