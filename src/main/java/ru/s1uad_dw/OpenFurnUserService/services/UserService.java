package ru.s1uad_dw.OpenFurnUserService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.s1uad_dw.OpenFurnUserService.models.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.CreateUserDto;
import ru.s1uad_dw.OpenFurnUserService.dtos.ViewUserDto;
import ru.s1uad_dw.OpenFurnUserService.exceptions.InvalidDataException;
import ru.s1uad_dw.OpenFurnUserService.exceptions.ResourceNotFoundException;
import ru.s1uad_dw.OpenFurnUserService.exceptions.UserAlreadyRegisteredException;
import ru.s1uad_dw.OpenFurnUserService.mappers.UserMappers;
import ru.s1uad_dw.OpenFurnUserService.repositories.UserRepository;
import ru.s1uad_dw.OpenFurnUserService.validators.UserValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public UUID createUser(CreateUserDto dto) {
        UserValidator.validateCreateUserDto(dto);
        if (isRegistered(dto.getEmail(), dto.getUsername(), dto.getPhone()))
            throw new UserAlreadyRegisteredException("User already registered");
        return userRepository.save(UserMappers.createUserDtoToDao(dto)).getId();
    }

    public List<ViewUserDto> findAll() {
        return userRepository.findAll().stream().map(UserMappers::DaoToViewUserDto).toList();
    }

    public ViewUserDto findByToken(String token) {
        tokenService.checkTokenExpiration(token);

        UUID id = tokenService.getId(token);
        User dao = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMappers.DaoToViewUserDto(dao);
    }

    public String updateByToken(String token, User fieldsToUpdate) {
        tokenService.checkTokenExpiration(token);

        UUID id = tokenService.getId(token);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (fieldsToUpdate.getUsername() != null) user.setUsername(fieldsToUpdate.getUsername());
        if (fieldsToUpdate.getEmail() != null) user.setEmail(fieldsToUpdate.getEmail());
        if (fieldsToUpdate.getPhone() != null) user.setPhone(fieldsToUpdate.getPhone());
        if (fieldsToUpdate.getName() != null) user.setName(fieldsToUpdate.getName());
        if (fieldsToUpdate.getSurname() != null) user.setSurname(fieldsToUpdate.getSurname());
        if (fieldsToUpdate.getPatronymic() != null) user.setPatronymic(fieldsToUpdate.getPatronymic());
        if (fieldsToUpdate.getBalance() != 0) user.setBalance(fieldsToUpdate.getBalance());
        if (fieldsToUpdate.getCardNumber() != null) user.setCardNumber(fieldsToUpdate.getCardNumber());

        userRepository.save(user);
        return "Success";
    }

    public String deleteByToken(String token){
        tokenService.checkTokenExpiration(token);

        UUID id = tokenService.getId(token);
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return "Success";
        }
        throw new ResourceNotFoundException("User not found");
    }

    public UUID getIdByLoginAndPassword(String login, String password) {
        User user = userRepository.findByEmailOrUsernameOrPhone(login, login, login).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        if(user.getPassword().equals(password)){
            return user.getId();
        } throw new InvalidDataException("Invalid password");
    }

    public boolean isRegistered(String email, String username, String phone) {
        Optional<User> dao = userRepository.findByEmailOrUsernameOrPhone(email, username, phone);
        if (dao.isPresent()){
            if (email.equals(dao.get().getEmail()))
                throw new UserAlreadyRegisteredException("User with specified email already registered");

            if (username.equals(dao.get().getUsername()))
                throw new UserAlreadyRegisteredException("User with specified username already registered");

            if (phone.equals(dao.get().getPhone()))
                throw new UserAlreadyRegisteredException("User with specified phone already registered");
        }
        return false;
    }
}
