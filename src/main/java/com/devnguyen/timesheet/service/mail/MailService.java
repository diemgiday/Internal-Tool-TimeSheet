package com.devnguyen.timesheet.service.mail;

import com.devnguyen.timesheet.dto.request.MailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendmail (String mail, MailRequest mailRequest){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(mailRequest.getSubject());
        simpleMailMessage.setText(mailRequest.getContent());

        mailSender.send(simpleMailMessage);
    }

}
