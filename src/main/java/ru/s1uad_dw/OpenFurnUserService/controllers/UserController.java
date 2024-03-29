package ru.s1uad_dw.OpenFurnUserService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create user by email | phone | username and password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New user id:UUID",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email | phone | username | password",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already registered",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public UUID createUser(@RequestBody CreateUserDto dto){
        return userService.createUser(dto);
    }
        //READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find user by access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User data",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid token",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token expired",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ViewUserDto findByToken(@RequestHeader("Authorization") String token){
        return userService.findByToken(token);
    }
        //UPDATE
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user by access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid token",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token expired",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public String updateByToken(@RequestHeader("Authorization") String token, @RequestBody User fieldsToUpdate){
        return userService.updateByToken(token, fieldsToUpdate);
    }
        //DELETE
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete user by access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid token",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token expired",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public String deleteByToken(@RequestHeader("Authorization") String token){
        return userService.deleteByToken(token);
    }

    //OTHER
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user id by login and password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User id:UUID",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid password",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public UUID getIdByLoginAndPassword(@RequestBody VerifyUserDto dto){
        return userService.getIdByLoginAndPassword(dto.getLogin(), dto.getPassword());
    }
}
