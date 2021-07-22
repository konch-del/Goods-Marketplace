package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.users.UsersService;
import java.util.List;


/**
 * Контроллер для REST-API пользователя
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Создает нового пользователя
     * @param usersDto JSON-обект пользователя
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveUsers(@RequestBody UsersAdminDTO usersDto) {
        return usersService.saveUser(usersDto);
    }

    /**
     * Выводит всех пользователей
     * @return информацию о всех пользователях
     */
    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> findAllUsers() {
        return usersService.findAll();
    }

    /**
     * Поиск пользователя по логину
     * @param username логин пользователя
     * @return пользователя
     */
    @GetMapping("/findByLogin")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> findByLogin(@RequestParam String username) {
        return usersService.findByUsername(username);
    }

    /**
     * Удаляет пользователя по id
     * @param id удаляемого пользователя
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteUsers(@RequestParam Long id) {
        return usersService.deleteUser(id);
    }

    /**
     * Смена информации о пользователе
     * @param usersDto JSON-обект пользователя
     * @return
     */
    @PostMapping("/changeinfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeUser(@RequestBody UsersPrivatDTO usersDto){
        return usersService.changeInfo(usersDto);
    }

    /**
     * Смена активации аккаунта
     * @param id аккаунта
     * @param activ значение активации
     * @return
     */
    @PostMapping("/changeActivity")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeActiv(@RequestParam Long id, @RequestParam Integer activ){
        return usersService.changeActivation(id, activ);
    }

    /**
     * Смена магазина
     * @param id аккаунта
     * @param shop id магазина
     * @return
     */
    @PostMapping("/changeShop")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeShop(@RequestParam Long id, @RequestParam Long shop){
        return usersService.changeShop(id, shop);
    }

    /**
     * Смена пароля
     * @param user пользователь, вызвавший метод
     * @param pass пароль
     * @param pass2 повторение пароля
     * @return
     */
    @PostMapping("/changePass")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> changePass(@AuthenticationPrincipal User user, @RequestParam String pass, @RequestParam String pass2, @RequestParam String pass3){
        return usersService.changePassword(user, pass, pass2, pass3);
    }

    /**
     * Смена почты
     * @param user пользователь, вызвавший метод
     * @param pass пароль
     * @param email новая почта
     * @return
     */
    @PostMapping("/changeEmail")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> changeEmail(@AuthenticationPrincipal User user, @RequestParam String pass, @RequestParam String email){
        return usersService.changeEmail(user, pass, email);
    }

    /**
     * Получение информации о пользователе по id
     * @param user пользователь, вызвавший метод
     * @param id искомого пользователя
     * @return информации о пользователе в соответствии с уровнем доступа
     */
    @GetMapping("/getUserById")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public ResponseEntity<?> getUserById(@AuthenticationPrincipal User user, @RequestParam Long id){
        return usersService.getUserById(user, id);
    }
}
