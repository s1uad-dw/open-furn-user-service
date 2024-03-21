package ru.s1uad_dw.OpenFurnUserService.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.s1uad_dw.OpenFurnUserService.dtos.AppError;
import ru.s1uad_dw.OpenFurnUserService.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(new AppError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURL().toString()
        ), HttpStatus.NOT_FOUND);
    }
}
