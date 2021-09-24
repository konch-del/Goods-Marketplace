package ru.netcracker.studentsummer2021.goodsmarketplace.service.users;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;

import java.util.List;

public interface UsersService {

    ResponseEntity<?> whoAmI(User user);

    ResponseEntity<?> saveUser(UsersAdminDTO userDTO);

    ResponseEntity<?> deleteUser(Long userId);

    ResponseEntity<?> findByUsername(String login);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long userId);

    ResponseEntity<?> changeActivation(Long userId, Integer active);

    ResponseEntity<?> changeInfo(User user, UsersPrivatDTO userDTO);

    ResponseEntity<?> changeEmail(User activeUser, String password, String newEmail);

    ResponseEntity<?> changePassword(User activeUser, String password, String password2, String password3);

    ResponseEntity<?> changeShop(Long userId, Long shopId);

    ResponseEntity<?> getUserById(User activeUser, Long userId);

}
