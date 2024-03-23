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

import java.util.List;
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
    public List<ViewUserDto> findAll(){
        return userService.findAll();
    }
    @GetMapping("{id}")
    public ViewUserDto findById(@PathVariable UUID id){
        return userService.findById(id);
    }
        //UPDATE
    @PatchMapping("{id}")
    public UUID update(@PathVariable UUID id, @RequestBody User fieldsToUpdate){
        return userService.update(id, fieldsToUpdate);
    }
        //DELETE
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id){
        userService.deleteById(id);
    }

    @GetMapping("{id}/verify")
    public boolean verifyUser(@RequestBody VerifyUserDto dto){
        return userService.verifyUser(dto.getLogin(), dto.getPassword());
    }
    @GetMapping("check_registration")
    public boolean checkRegistration(@RequestParam String login){
        return userService.isRegistered(login);
    }
}
