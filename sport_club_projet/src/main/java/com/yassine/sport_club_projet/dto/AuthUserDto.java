package com.yassine.sport_club_projet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthUserDto {

    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;
    @Min(value = 12,message = "password should be at least 12 characters")
    private String password;

}
