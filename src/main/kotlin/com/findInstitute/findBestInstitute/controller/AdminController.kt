package com.findInstitute.findBestInstitute.controller

import com.findInstitute.findBestInstitute.model.Admin
import com.findInstitute.findBestInstitute.service.AdminService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/admin")
@CrossOrigin("https://find-best-institute.herokuapp.com/")
class AdminController(var adminService: AdminService) {
    @PostMapping
    fun addAdmin(@Valid @RequestBody admin: Admin): Admin = this.adminService.addAdmin(admin.email, admin.password)

    @GetMapping("/{email}")
    fun getAdmin(@PathVariable email: String): Admin = this.adminService.viewAdmin(email)

    @PutMapping("/{email}")
    fun updateAdmin(@PathVariable email: String, @Valid @RequestBody admin: Admin): Admin =
        this.adminService.updateAdmin(email, admin)

    @DeleteMapping("/{email}")
    fun deleteAdmin(@PathVariable email: String): String = this.adminService.deleteAdmin(email)

    @PutMapping("/{email}/{oldPassword}/{newPassword}")
    fun changePassword(
        @PathVariable email: String, @PathVariable oldPassword: String, @PathVariable newPassword: String
    ): String = this.adminService.changePassword(email, oldPassword, newPassword)
}