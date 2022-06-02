package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.model.Course

interface CourseService {
    fun addCourse(course: Course): Course
    fun viewCourseByEmail(insEmail: String): List<Course>
    fun deleteCourseById(courseId: String): String
}