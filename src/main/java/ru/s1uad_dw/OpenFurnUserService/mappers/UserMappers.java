package ru.s1uad_dw.OpenFurnUserService.mappers;

import ru.s1uad_dw.OpenFurnUserService.models.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.CreateUserDto;
import ru.s1uad_dw.OpenFurnUserService.dtos.ViewUserDto;

public class UserMappers {
    public static User createUserDtoToDao(CreateUserDto dto){
        return new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPassword()
        );
    }

    public static ViewUserDto DaoToViewUserDto(User dao){
        return new ViewUserDto(
                dao.getId(),
                dao.getUsername(),
                dao.getEmail(),
                dao.getPhone(),
                dao.getName(),
                dao.getSurname(),
                dao.getPatronymic(),
                dao.getBalance(),
                dao.getCardNumber()
        );
    }
}
