package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.dto.InstituteDto
import com.findInstitute.findBestInstitute.model.Institute
import com.findInstitute.findBestInstitute.service.InstituteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/institute")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class InstituteController(var instituteService: InstituteService) {
    @PostMapping
    fun addInstitute(@Valid @RequestBody institute: Institute): ResponseEntity<Institute> {
        return ResponseEntity(this.instituteService.addInstitute(institute), HttpStatus.OK)
    }

    @GetMapping
    fun getAllInstitutes(): List<Institute> {
        return this.instituteService.viewAllInstitute()
    }

    @GetMapping("/city/{city}")
    fun getInstitutesByCity(@PathVariable city: String): List<Institute> {
        return this.instituteService.viewAllByInstituteCity(city)
    }

    @GetMapping("/{email}")
    fun getInstitute(@PathVariable email: String): Institute = this.instituteService.viewInstituteByEmail(email)

    @DeleteMapping("/{email}")
    fun deleteInstitute(@PathVariable email: String): String = this.instituteService.deleteInstitute(email)

    @PutMapping("/{email}")
    fun updateInstitute(@PathVariable email: String, @Valid @RequestBody institute: InstituteDto): Institute =
        this.instituteService.updateInstitute(email, institute)

    @PutMapping("/{email}/{oldPassword}/{newPassword}")
    fun changeInstitutePassword(
        @PathVariable email: String,
        @PathVariable oldPassword: String,
        @PathVariable newPassword: String
    ): Institute {
        return this.instituteService.changePassword(email, oldPassword, newPassword)
    }
}
