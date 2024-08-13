package com.devnguyen.timesheet.controller.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulerController {

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 25 15 * * *") 
    public void schedulerMail() {
        log.info("This email will be sent at 14:09 everyday");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("devnguyen.jp@gmail.com");
        message.setSubject("Test Email");
        message.setText("This is a mail test will be sent at 14:09 everyday");
        mailSender.send(message);
    }
}
