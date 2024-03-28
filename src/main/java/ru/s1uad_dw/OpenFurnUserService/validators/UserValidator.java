package ru.s1uad_dw.OpenFurnUserService.validators;

import ru.s1uad_dw.OpenFurnUserService.daos.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.CreateUserDto;
import ru.s1uad_dw.OpenFurnUserService.exceptions.InvalidDataException;

import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX_PATTERN = "^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$";

    private static final String USERNAME_REGEX_PATTERN = "^[a-zA-Z0-9!'\\(\\)*+\\-.\\/<=>?@\\[\\]^_`{|}~]{3,31}$";
    private static final String PASSWORD_REGEX_PATTERN = "^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d).*$";

    public static void validateCreateUserDto(CreateUserDto dto) {
        validateEmail(dto.getEmail());
        validatePhone(dto.getPhone());
        validateUsername(dto.getUsername());
        validatePassword(dto.getPassword());
    }

    static void validateEmail(String emailString) {
        if (!patternMatches(emailString, EMAIL_REGEX_PATTERN))
            throw new InvalidDataException("Invalid email");
    }

    static void validatePhone(String phoneString) {
        if (!patternMatches(phoneString, PHONE_REGEX_PATTERN))
            throw new InvalidDataException("Invalid phone");
    }

    static void validateUsername(String usernameString) {
        if (!patternMatches(usernameString, USERNAME_REGEX_PATTERN))
            throw new InvalidDataException("Invalid username");
    }

    static void validatePassword(String passwordString) {
        if (!patternMatches(passwordString, PASSWORD_REGEX_PATTERN))
            throw new InvalidDataException("Invalid password");
    }

    static boolean patternMatches(String stringToMatch, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(stringToMatch)
                .matches();
    }
}
