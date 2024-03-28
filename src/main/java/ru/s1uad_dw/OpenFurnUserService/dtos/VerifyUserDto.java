package ru.s1uad_dw.OpenFurnUserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class VerifyUserDto {
    private String login;
    private String password;
}
