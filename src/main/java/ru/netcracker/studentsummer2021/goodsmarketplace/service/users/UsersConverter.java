package ru.netcracker.studentsummer2021.goodsmarketplace.service.users;

import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;

/**
 * Класс конвертертатор между пользовательскими отображениями и отображениями в БД
 */
@Component
public class UsersConverter {

    /**
     * Конвертирует Users в экземпляр UsersPublicDTO
     * @param user экземпляр
     * @return  экземпляр
     */
    public UsersPublicDTO fromUserToUserPublic(Users user){
        UsersPublicDTO usersDTO = new UsersPublicDTO();
        usersDTO.setId(user.getId());
        usersDTO.setUsername(user.getUsername());
        usersDTO.setFcs(user.getFcs());
        usersDTO.setCity(user.getCity());
        return usersDTO;
    }

    /**
     * Конвертирует UsersPublicDTO в экземпляр Users
     * @param usersDto
     * @return Users экземпляр
     */
    public Users fromUserDtoToUser(UsersPrivatDTO usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setEmail(usersDto.getEmail());
        users.setUsername(usersDto.getUsername());
        users.setFcs(usersDto.getFcs());
        users.setCity(usersDto.getCity());
        users.setAddress(usersDto.getAddress());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setShopId(usersDto.getShopId());
        users.setActivation(usersDto.isActivation());
        return users;
    }

    /**
     * Конвертирует Users в экземпляр UsersPrivatDTO
     * @param users экземпляр
     * @return UsersPrivatDTO экземпляр
     */
    public UsersPrivatDTO fromUserToUserDto(Users users) {
        UsersPrivatDTO usersDTO = new UsersPrivatDTO();
        usersDTO.setId(users.getId());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setFcs(users.getFcs());
        usersDTO.setCity(users.getCity());
        usersDTO.setAddress(users.getAddress());
        usersDTO.setPhoneNumber(users.getPhoneNumber());
        usersDTO.setShopId(users.getShopId());
        usersDTO.setActivation(users.isActivation());
        return usersDTO;
    }

    /**
     * Конвертирует Users в экземпляр UsersAdminDTO
     * @param users экземпляр
     * @return UsersAdminDTO экземпляр
     */
    public UsersAdminDTO fromUserToUserAdmin(Users users){
        UsersAdminDTO usersDTO = new UsersAdminDTO();
        usersDTO.setId(users.getId());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setFcs(users.getFcs());
        usersDTO.setCity(users.getCity());
        usersDTO.setAddress(users.getAddress());
        usersDTO.setPhoneNumber(users.getPhoneNumber());
        usersDTO.setShopId(users.getShopId());
        usersDTO.setActivation(users.isActivation());
        usersDTO.setAccountType(users.getAccountType());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setDateOfCreation(users.getDateOfCreation());
        usersDTO.setLastLoginDate(users.getLastLoginDate());
        return usersDTO;
    }

}
