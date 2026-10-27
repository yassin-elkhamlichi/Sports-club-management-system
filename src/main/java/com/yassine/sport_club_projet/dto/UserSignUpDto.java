package com.yassine.sport_club_projet.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSignUpDto {
    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should be at least 8 characters")
    private String password;

    @NotBlank(message = "first name is required")
    private String firstname;

    @NotBlank(message = "last name is required")

    private String lastname;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Must contain exactly 10 digits")
    private String phoneNumber;
}
