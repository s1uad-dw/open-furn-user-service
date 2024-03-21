package ru.s1uad_dw.OpenFurnUserService.daos;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Nullable
    private String username;
    @Nullable
    private String email;
    @Nullable
    private String phone;
    private String password;
    @Nullable
    private String name;
    @Nullable
    private String surname;
    private String patronymic;
    private float balance;
    @Nullable
    private String cardNumber;
}
