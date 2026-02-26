package com.yassine.sport_club_projet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateMemberRequestDto {
    private LocalDate birthDate;
}
