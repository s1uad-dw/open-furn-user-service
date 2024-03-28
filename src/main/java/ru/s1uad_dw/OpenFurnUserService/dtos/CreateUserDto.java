package ru.s1uad_dw.OpenFurnUserService.dtos;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String email;
    private String phone;
    private String password;
}
