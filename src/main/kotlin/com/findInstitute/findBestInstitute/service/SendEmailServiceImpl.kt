package com.findInstitute.findBestInstitute.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class SendEmailServiceImpl(
    @Autowired
    var mailSender: JavaMailSender
) {

    fun sendMail(toMail: String, body: String, subject: String) {
        var message: SimpleMailMessage = SimpleMailMessage()
        message.setFrom("sahumohit@gmail.com")
        message.setTo(toMail)
        message.setText(body)
        message.setSubject(subject)
        mailSender.send(message)
    }
}