package com.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterRequestDTO {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}
