package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.model.Course
import com.findInstitute.findBestInstitute.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/course")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class CourseController(var courseService: CourseService) {
    @PostMapping
    fun addCourse(@RequestBody course: Course): ResponseEntity<Course> {
        return ResponseEntity(this.courseService.addCourse(course), HttpStatus.OK)
    }

    @GetMapping("/{insEmail}")
    fun getAllCoursesByEmail(@PathVariable insEmail: String): List<Course> =
        this.courseService.viewCourseByEmail(insEmail)

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: String): String = this.courseService.deleteCourseById(courseId)
}