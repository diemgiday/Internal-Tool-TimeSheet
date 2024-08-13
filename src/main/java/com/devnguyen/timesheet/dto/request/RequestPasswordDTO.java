package com.devnguyen.timesheet.dto.request;

import lombok.Getter;


@Getter
public class RequestPasswordDTO {

    private String secretKey;
    private String password;
    private String confirmPassword;

}
