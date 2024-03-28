package ru.s1uad_dw.OpenFurnUserService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.s1uad_dw.OpenFurnUserService.daos.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.CreateUserDto;
import ru.s1uad_dw.OpenFurnUserService.dtos.VerifyUserDto;
import ru.s1uad_dw.OpenFurnUserService.dtos.ViewUserDto;
import ru.s1uad_dw.OpenFurnUserService.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user_service")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    //CRUD
        //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@RequestBody CreateUserDto dto){
        return userService.createUser(dto);
    }
        //READ
    @GetMapping
    public ViewUserDto findByToken(@RequestHeader("Authorization") String token){
        return userService.findByToken(token);
    }
        //UPDATE
    @PatchMapping
    public String updateByToken(@RequestHeader("Authorization") String token, @RequestBody User fieldsToUpdate){
        return userService.updateByToken(token, fieldsToUpdate);
    }
        //DELETE
    @DeleteMapping
    public String deleteByToken(@RequestHeader("Authorization") String token){
        return userService.deleteByToken(token);
    }

    @PostMapping("login")
    public UUID getIdByLoginAndPassword(@RequestBody VerifyUserDto dto){
        return userService.getIdByLoginAndPassword(dto.getLogin(), dto.getPassword());
    }
}
