package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.DAO.RatingDAO
import com.findInstitute.findBestInstitute.exception.AddException
import com.findInstitute.findBestInstitute.exception.GetEmptyException
import com.findInstitute.findBestInstitute.model.Rating
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(
    var ratingDAO: RatingDAO,
    var instituteDAO: InstituteDAO
) : RatingService {
    override fun addRating(rating: Rating): Rating {
        if (instituteDAO.existsByEmail(rating.insEmail))
            return this.ratingDAO.save(rating)
        else
            throw AddException("Institute does not exist")
    }

    override fun viewRatingByInsEmail(insEmail: String): List<Rating> {
        if (ratingDAO.count() > 0)
            return this.ratingDAO.findByInsEmail(insEmail)
        else
            throw GetEmptyException("No Rating Found")
    }

    override fun viewAllRating(): List<Rating> {
        if (instituteDAO.count() > 0)
            return this.ratingDAO.findAll()
        else
            throw GetEmptyException("No Rating Found")
    }

}