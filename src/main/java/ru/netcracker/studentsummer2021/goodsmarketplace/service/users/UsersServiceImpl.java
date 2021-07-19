package ru.netcracker.studentsummer2021.goodsmarketplace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.security.UserSecurity;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Класс, реализуюющий бизнес-логику
 */
@Service("usersDetailsServiceImpl")
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UsersConverter usersConverter) {
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
    }

    /**
     * Сохраняет пользователя в БД
     * @param userDTO объект получений при запросе
     * @return сохраненного пользователя
     */
    @Override
    public UsersPrivatDTO saveUser(UsersAdminDTO userDTO) {
        validateUserDto(userDTO);
        Users user = usersConverter.fromUserDtoToUser(userDTO);
        user.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
        user.setAccountType(userDTO.getAccountType());
        user.setDateOfCreation(new GregorianCalendar());
        user.setLastLoginDate(new GregorianCalendar());
        Users savedUser = usersRepository.save(user);
        return usersConverter.fromUserToUserDto(savedUser);
    }

    /**
     * Валидирует данные пользователя
     * @param usersDto объект получений при запросе
     * @throws NullPointerException
     */
    private void validateUserDto(UsersPrivatDTO usersDto) throws NullPointerException {
        if (isNull(usersDto)) {
            throw new NullPointerException();
        }
        if (isNull(usersDto.getUsername()) || usersDto.getUsername().isEmpty()) {
            throw new NullPointerException();
        }
    }

    /**
     * Удаляет пользователя по id
     * @param userId id удаляемого пользователя
     */
    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    /**
     * Ищет пользователя по логину
     * @param login логин пользователя
     * @return чувствиетльную информацию о пользователе
     */
    @Override
    public UsersPrivatDTO findByUsername(String login) {
        Users users = usersRepository.findByUsername(login);
        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }
        return null;
    }

    /**
     * Выводит всех пользователей
     * @return чувствиетльную информацию о пользователях
     */
    @Override
    public List<UsersPrivatDTO> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Поиск пользователя по id
     * @param userId id пользователя
     * @return чувствиетльную информацию о пользователе
     */
    @Override
    public UsersPrivatDTO findById(Long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        return users.map(usersConverter::fromUserToUserDto).orElse(null);
    }

    /**
     * Смена статуса активации
     * @param userId id пользователя
     * @param active статус активации
     */
    @Override
    public void changeActivation(Long userId, Integer active) {
        usersRepository.changeActiv(userId, active);
    }

    /**
     * Смена информации о пользователе
     * @param userDTO объект получений при запросе
     */
    @Override
    public void changeInfo(UsersPrivatDTO userDTO) {
        validateUserDto(userDTO);
        Users user = usersRepository.findById(userDTO.getId()).get();
        if(userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getAddress() != null){
            user.setAddress(userDTO.getAddress());
        }
        if(userDTO.getPhoneNumber() != null){
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if(userDTO.getCity() != null){
            user.setCity(userDTO.getCity());
        }
        if(userDTO.getFcs() != null){
            user.setFcs(userDTO.getFcs());
        }
        usersRepository.save(user);
    }

    /**
     * Смена почты
     * @param activeUser пользователь, вызывающий метод
     * @param password пароль пользователя
     * @param newEmail новая почта
     */
    @Override
    public void changeEmail(User activeUser, String password, String newEmail) {
        Users user = usersRepository.findByUsername(activeUser.getUsername());
        if(user.getPassword().equals(password)){
            usersRepository.changeEmail(user.getId(), newEmail);
        }
    }

    /**
     * Смена пароля
     * @param activeUser
     * @param password
     * @param password2
     */
    @Override
    public void changePassword(User activeUser, String password, String password2) {
        Users user = usersRepository.findByUsername(activeUser.getUsername());
        if(password.equals(password2) && password.equals(user.getPassword())){
            usersRepository.changePass(user.getId(), password);
        }
    }

    /**
     * Смена магазина
     * @param userId id пользователя, которому меняют магазин
     * @param shopId id магазина
     */
    @Override
    public void changeShop(Long userId, Long shopId) {
        usersRepository.changeShop(userId, shopId);
    }

    /**
     * Получение информации о пользователе по id
     * @param activeUser пользователь, вызывающий метод
     * @param userId id искомого пользователя
     * @return информации о пользователе в зависимости от прав
     */
    @Override
    public UsersDTO getUserById(User activeUser, Long userId){
        Users user = usersRepository.findById(userId).get();
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return usersConverter.fromUserToUserAdmin(user);
        }
        if(usersRepository.findByUsername(activeUser.getUsername()).getId().equals(userId)){
            return usersConverter.fromUserToUserDto(user);
        }else {
            return usersConverter.fromUserToUserPublic(user);
        }
    }

    /**
     * Находит пользователя по логину, почте или телефону
     * @param username логин, почта или телефон
     * @return данные для авторизации
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username);
        return UserSecurity.fromUser(user);
    }
}
