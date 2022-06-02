package com.findInstitute.findBestInstitute

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class FindBestInstituteApplication

fun main(args: Array<String>) {
    runApplication<FindBestInstituteApplication>(*args)
}
