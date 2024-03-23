package ru.s1uad_dw.OpenFurnUserService.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
}
