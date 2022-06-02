package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Course
import org.springframework.data.mongodb.repository.MongoRepository

interface CourseDAO : MongoRepository<Course, String> {
    //    fun existByEmailAndCourse(insEmail: String, courseName : String) : Boolean
//    fun findByCourseName(courseName: String) : List<Course>
    fun existsByInsEmail(insEmail: String): Boolean
    fun findByInsEmail(insEmail: String): List<Course>

}