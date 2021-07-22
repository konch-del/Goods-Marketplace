package ru.netcracker.studentsummer2021.goodsmarketplace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ShopRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.security.UserSecurity;

import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, реализуюющий бизнес-логику
 */
@Service("usersDetailsServiceImpl")
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;
    private final ShopRepository shopRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UsersConverter usersConverter, ShopRepository shopRepository) {
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
        this.shopRepository = shopRepository;
    }

    /**
     * Сохраняет пользователя в БД
     * @param userDTO объект получений при запросе
     * @return сохраненного пользователя
     */
    @Override
    public ResponseEntity<?> saveUser(UsersAdminDTO userDTO) {
        if(validateUserDto(userDTO)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Users user = usersConverter.fromUserDtoToUser(userDTO);
        user.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
        user.setAccountType(userDTO.getAccountType());
        user.setDateOfCreation(new GregorianCalendar());
        user.setLastLoginDate(new GregorianCalendar());
        Users savedUser = usersRepository.save(user);
        return new ResponseEntity<>(usersConverter.fromUserToUserDto(savedUser), HttpStatus.CREATED);
    }

    /**
     * Валидирует данные пользователя
     * @param usersDto объект получений при запросе
     */
    private boolean validateUserDto(UsersAdminDTO usersDto){
        if (usersDto.getUsername().equals("") || usersDto.getEmail().equals("")
            || usersDto.getPassword().equals("") || usersDto.getFcs().equals("")
            || usersDto.getCity().equals("") || usersDto.getPhoneNumber().equals("")) {
            return false;
        }else return true;
    }

    /**
     * Удаляет пользователя по id
     * @param userId id удаляемого пользователя
     */
    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        if(usersRepository.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Ищет пользователя по логину
     * @param login логин пользователя
     * @return чувствиетльную информацию о пользователе
     */
    @Override
    public ResponseEntity<?> findByUsername(String login) {
        Users users = usersRepository.findByUsername(login);
        if (users != null) {
            return new ResponseEntity<>(usersConverter.fromUserToUserDto(users), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Выводит всех пользователей
     * @return чувствиетльную информацию о пользователях
     */
    @Override
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Поиск пользователя по id
     * @param userId id пользователя
     * @return чувствиетльную информацию о пользователе
     */
    @Override
    public ResponseEntity<?> findById(Long userId) {
        if(usersRepository.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Users> users = usersRepository.findById(userId);
        return new ResponseEntity<>(users.map(usersConverter::fromUserToUserDto).orElse(null), HttpStatus.OK);
    }

    /**
     * Смена статуса активации
     * @param userId id пользователя
     * @param active статус активации
     */
    @Override
    public ResponseEntity<?> changeActivation(Long userId, Integer active) {
        if(usersRepository.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersRepository.changeActiv(userId, active);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Смена информации о пользователе
     * @param userDTO объект получений при запросе
     */
    @Override
    public ResponseEntity<?> changeInfo(UsersPrivatDTO userDTO) {
        if(usersRepository.findById(userDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        return new ResponseEntity<>(usersRepository.save(user), HttpStatus.OK);
    }

    /**
     * Смена почты
     * @param activeUser пользователь, вызывающий метод
     * @param password пароль пользователя
     * @param newEmail новая почта
     */
    @Override
    public ResponseEntity<?> changeEmail(User activeUser, String password, String newEmail) {
        Users user = usersRepository.findByUsername(activeUser.getUsername());
        if(user.getPassword().equals(password)){
            usersRepository.changeEmail(user.getId(), newEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Смена пароля
     * @param activeUser
     * @param password
     * @param password2
     */
    @Override
    public ResponseEntity<?> changePassword(User activeUser, String password, String password2, String password3) {
        Users user = usersRepository.findByUsername(activeUser.getUsername());
        if(password.equals(password2) && password.equals(user.getPassword())){
            usersRepository.changePass(user.getId(), password3);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Смена магазина
     * @param userId id пользователя, которому меняют магазин
     * @param shopId id магазина
     */
    @Override
    public ResponseEntity<?> changeShop(Long userId, Long shopId) {
        if(usersRepository.findById(userId).isEmpty() || shopRepository.findById(shopId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersRepository.changeShop(userId, shopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Получение информации о пользователе по id
     * @param activeUser пользователь, вызывающий метод
     * @param userId id искомого пользователя
     * @return информации о пользователе в зависимости от прав
     */
    @Override
    public ResponseEntity<?> getUserById(User activeUser, Long userId){
        if(usersRepository.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Users user = usersRepository.findById(userId).get();
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(usersConverter.fromUserToUserAdmin(user), HttpStatus.OK);
        }
        if(usersRepository.findByUsername(activeUser.getUsername()).getId().equals(userId)){
            return new ResponseEntity<>(usersConverter.fromUserToUserDto(user), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(usersConverter.fromUserToUserPublic(user), HttpStatus.OK);
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
