package com.sonnguyen.base.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {

    @Size(min = 8, message = "Username min 8")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
