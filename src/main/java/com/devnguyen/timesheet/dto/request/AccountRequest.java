package com.devnguyen.timesheet.dto.request;

import javax.validation.constraints.NotBlank;

import com.devnguyen.timesheet.enums.Role;

import lombok.Getter;

@Getter
public class AccountRequest {

    @NotBlank(message = "username must be not black")
    private String username;

    @NotBlank(message = "password must be not black")
    private String password;

    @NotBlank(message = "email must be not black")
    private String email;
    
    private Role role ;

}
