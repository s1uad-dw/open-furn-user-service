package ru.s1uad_dw.OpenFurnUserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class ViewUserDto {
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private String name;
    private String surname;
    private String patronymic;
    private float balance;
    private String cardNumber;
}
