package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.model.Rating

interface RatingService {
    fun addRating(rating: Rating): Rating
    fun viewRatingByInsEmail(insEmail: String): List<Rating>
    fun viewAllRating(): List<Rating>
}