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
    public UsersPrivatDTO saveUsers(@RequestBody UsersAdminDTO usersDto) {
        return usersService.saveUser(usersDto);
    }

    /**
     * Выводит всех пользователей
     * @return информацию о всех пользователях
     */
    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('admin')")
    public List<UsersPrivatDTO> findAllUsers() {
        return usersService.findAll();
    }

    /**
     * Поиск пользователя по логину
     * @param username логин пользователя
     * @return пользователя
     */
    @GetMapping("/findByLogin")
    @PreAuthorize("hasAuthority('user')")
    public UsersPrivatDTO findByLogin(@RequestParam String username) {
        return usersService.findByUsername(username);
    }

    /**
     * Удаляет пользователя по id
     * @param id удаляемого пользователя
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> deleteUsers(@RequestParam Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Смена информации о пользователе
     * @param usersDto JSON-обект пользователя
     * @return
     */
    @PostMapping("/changeinfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> changeUser(@RequestBody UsersPrivatDTO usersDto){
        usersService.changeInfo(usersDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Смена активации аккаунта
     * @param id аккаунта
     * @param activ значение активации
     * @return
     */
    @PostMapping("/changeActivity")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> changeActiv(@RequestParam Long id, @RequestParam Integer activ){
        usersService.changeActivation(id, activ);
        return ResponseEntity.ok().build();
    }

    /**
     * Смена магазина
     * @param id аккаунта
     * @param shop id магазина
     * @return
     */
    @PostMapping("/changeShop")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Void> changeShop(@RequestParam Long id, @RequestParam Long shop){
        usersService.changeShop(id, shop);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> changePass(@AuthenticationPrincipal User user, @RequestParam String pass, @RequestParam String pass2){
        usersService.changePassword(user, pass, pass2);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> changeEmail(@AuthenticationPrincipal User user, @RequestParam String pass, @RequestParam String email){
        usersService.changeEmail(user, pass, email);
        return ResponseEntity.ok().build();
    }

    /**
     * Получение информации о пользователе по id
     * @param user пользователь, вызвавший метод
     * @param id искомого пользователя
     * @return информации о пользователе в соответствии с уровнем доступа
     */
    @GetMapping("/getUserById")
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    public UsersDTO getUserById(@AuthenticationPrincipal User user, @RequestParam Long id){
        return usersService.getUserById(user, id);
    }
}
