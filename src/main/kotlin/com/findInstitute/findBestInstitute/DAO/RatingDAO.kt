package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.Rating
import org.springframework.data.mongodb.repository.MongoRepository

interface RatingDAO : MongoRepository<Rating, String> {
    //    fun viewByInsEmail(insEmail : String) : List<Rating>
//    fun existByInsEmail(insEmail: String) : Boolean
    fun findByInsEmail(insEmail: String): List<Rating>

}
