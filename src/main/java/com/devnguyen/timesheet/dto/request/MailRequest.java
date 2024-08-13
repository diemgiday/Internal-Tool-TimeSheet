package com.devnguyen.timesheet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {

    private String subject;
    
    private String content;
}
