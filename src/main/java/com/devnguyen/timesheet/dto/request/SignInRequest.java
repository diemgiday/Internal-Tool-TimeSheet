package com.devnguyen.timesheet.dto.request;


import lombok.Data;
import lombok.Getter;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Data
public class SignInRequest implements Serializable {

    @NotBlank(message = "username must be not blank")
    private String username;

    @NotBlank(message = "password must be not blank")
    private String password;

//    private String Platform;


    public String getUserName() {
        return username;
    }

    public String getPassWord() {
        return password;
    }
}
