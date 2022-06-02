package com.findInstitute.findBestInstitute.service

import com.findInstitute.findBestInstitute.DAO.CourseDAO
import com.findInstitute.findBestInstitute.DAO.InstituteDAO
import com.findInstitute.findBestInstitute.exception.DeleteException
import com.findInstitute.findBestInstitute.exception.GetEmptyException
import com.findInstitute.findBestInstitute.exception.GetException
import com.findInstitute.findBestInstitute.model.Course
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    var courseDAO: CourseDAO,
    var instituteDAO: InstituteDAO
) : CourseService {
    override fun addCourse(course: Course): Course {
        if (this.instituteDAO.existsByEmail(course.insEmail))
            return this.courseDAO.save(course)
        else
            throw GetException("Institute does not exist")
    }

    override fun viewCourseByEmail(insEmail: String): List<Course> {
        if (this.courseDAO.existsByInsEmail(insEmail))
            return this.courseDAO.findByInsEmail(insEmail)
        else
            throw GetEmptyException("No Courses")
    }

    override fun deleteCourseById(courseId: String): String {
        if (this.courseDAO.existsById(courseId)) {
            this.courseDAO.deleteById(courseId)
            return "SuccessFully Deleted"
        } else
            throw DeleteException("Id does not exist")

    }
}