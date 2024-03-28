package ru.s1uad_dw.OpenFurnUserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
