package ru.netcracker.studentsummer2021.goodsmarketplace.service.users;

import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;

import java.util.List;

public interface UsersService {

    UsersPrivatDTO saveUser(UsersAdminDTO userDTO);

    void deleteUser(Long userId);

    UsersPrivatDTO findByUsername(String login);

    List<UsersPrivatDTO> findAll();

    UsersPrivatDTO findById(Long userId);

    void changeActivation(Long userId, Integer active);

    void changeInfo(UsersPrivatDTO userDTO);

    void changeEmail(User activeUser, String password, String newEmail);

    void changePassword(User activeUser, String password, String password2);

    void changeShop(Long userId, Long shopId);

    UsersDTO getUserById(User activeUser, Long userId);

}
