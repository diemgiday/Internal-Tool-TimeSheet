// package com.devnguyen.timesheet.controller.mail;

// import com.devnguyen.timesheet.dto.request.MailRequest;
// import com.devnguyen.timesheet.email.MailService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/mail")
// public class MailController {

//     @Autowired
//     public MailService mailService;

//     @PostMapping("/send/{mail}")
//     public String sendMail (@PathVariable String mail, @RequestBody MailRequest mailRequest) {
//         mailService.sendmail(mail, mailRequest);
//         return "Successfully !";
//     }

// }
