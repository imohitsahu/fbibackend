package com.findInstitute.findBestInstitute.DAO

import com.findInstitute.findBestInstitute.model.UniversalRating
import org.springframework.data.mongodb.repository.MongoRepository

interface UniversalRatingDAO : MongoRepository<UniversalRating, String>