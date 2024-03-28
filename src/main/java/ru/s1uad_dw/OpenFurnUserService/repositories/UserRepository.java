package ru.s1uad_dw.OpenFurnUserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.s1uad_dw.OpenFurnUserService.daos.User;
import ru.s1uad_dw.OpenFurnUserService.dtos.ViewUserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByEmailOrUsernameOrPhone(String email, String username, String phone);
}
