package ru.s1uad_dw.OpenFurnUserService.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
