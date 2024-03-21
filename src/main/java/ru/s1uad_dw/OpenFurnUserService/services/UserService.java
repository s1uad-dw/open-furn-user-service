package ru.s1uad_dw.OpenFurnUserService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.s1uad_dw.OpenFurnUserService.daos.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.CreateUserDto;
import ru.s1uad_dw.OpenFurnUserService.dtos.ViewUserDto;
import ru.s1uad_dw.OpenFurnUserService.exceptions.ResourceNotFoundException;
import ru.s1uad_dw.OpenFurnUserService.mappers.UserMappers;
import ru.s1uad_dw.OpenFurnUserService.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UUID createUser(CreateUserDto dto) {
        return userRepository.save(UserMappers.createUserDtoToDao(dto)).getId();
    }

    public List<ViewUserDto> findAll() {
        return userRepository.findAll().stream().map(UserMappers::DaoToViewUserDto).toList();
    }

    public ViewUserDto findById(UUID id) {
        User dao = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMappers.DaoToViewUserDto(dao);
    }

    public UUID update(UUID id, User fieldsToUpdate) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (fieldsToUpdate.getUsername() != null) user.setUsername(fieldsToUpdate.getUsername());
        if (fieldsToUpdate.getEmail() != null) user.setEmail(fieldsToUpdate.getEmail());
        if (fieldsToUpdate.getPhone() != null) user.setPhone(fieldsToUpdate.getPhone());
        if (fieldsToUpdate.getName() != null) user.setName(fieldsToUpdate.getName());
        if (fieldsToUpdate.getSurname() != null) user.setSurname(fieldsToUpdate.getSurname());
        if (fieldsToUpdate.getPatronymic() != null) user.setPatronymic(fieldsToUpdate.getPatronymic());
        if (fieldsToUpdate.getBalance() != 0) user.setBalance(fieldsToUpdate.getBalance());
        if (fieldsToUpdate.getCardNumber() != null) user.setCardNumber(fieldsToUpdate.getCardNumber());

        return userRepository.save(user).getId();
    }

    public void deleteById(UUID id){
        userRepository.deleteById(id);
    }

    public boolean verifyUser(String login, String password) {
        User user = userRepository.findByEmailOrUsernameOrPhone(login, login, login).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        return user.getPassword().equals(password);
    }

    public boolean checkRegistration(String login) {
        return userRepository.findByEmailOrUsernameOrPhone(login, login, login).isPresent();
    }
}
