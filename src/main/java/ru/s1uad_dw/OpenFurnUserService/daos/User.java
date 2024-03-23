package ru.s1uad_dw.OpenFurnUserService.daos;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private float balance;
    private String cardNumber;

    public User(String username, String email, String phone, String password){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.balance = 0;
        this.name = null;
        this.surname = null;
        this.patronymic = null;
        this.cardNumber = null;
    }
}
