package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.model.Rating
import com.findInstitute.findBestInstitute.service.RatingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rating")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class RatingController(var ratingService: RatingService) {
    @PostMapping
    fun addRating(@RequestBody rating: Rating): ResponseEntity<Rating> {
        return ResponseEntity(this.ratingService.addRating(rating), HttpStatus.OK)
    }

    @GetMapping
    fun getRating(): List<Rating> {
        return this.ratingService.viewAllRating()
    }

    @GetMapping("/{insEmail}")
    fun getRatingByInsEmail(@PathVariable insEmail: String): List<Rating> =
        this.ratingService.viewRatingByInsEmail(insEmail)
}