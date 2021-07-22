package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.shops.ShopsService;

import java.util.List;


/**
 * Контроллер для REST-API магазина
 */
@RestController
@RequestMapping("/shops")
public class ShopsController {
    private final ShopsService shopsService;

    public ShopsController(ShopsService shopsService) {
        this.shopsService = shopsService;
    }

    /**
     * Создает новый магазин
     * @param shopsPublicDTO JSON-объект магазина
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> saveShop(@RequestBody ShopsPublicDTO shopsPublicDTO){
        return shopsService.saveShop(shopsPublicDTO);
    }

    /**
     * Удаляет магазин по id
     * @param id удаляемого магазина
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteShop(@RequestParam Long id){
        return shopsService.deleteShop(id);
    }

    /**
     * Выводит все магазины
     * @param user пользователь, вызывающий метод
     * @return полную информацию о магазинах для администраторов, нескрытую для пользователей
     */
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@AuthenticationPrincipal User user){
        return shopsService.findAll(user);
    }

    /**
     * Поиск магазина по id
     * @param user пользователь, вызывающий метод
     * @param id магазина
     * @return полную информацию о магазине для администраторов, нескрытую для пользователей
     */
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@AuthenticationPrincipal User user, @RequestParam Long id){
        return shopsService.findById(user, id);
    }

    /**
     * Изменение информации о магазине
     * @param user пользователь, вызывающий метод
     * @param shopsPublicDTO JSON-объект магазина
     * @return
     */
    @PostMapping("/changeInfo")
    public ResponseEntity<?> changeInfo(@AuthenticationPrincipal User user, @RequestBody ShopsPublicDTO shopsPublicDTO){
        return shopsService.changeInfo(user, shopsPublicDTO);
    }

}
