package ru.netcracker.studentsummer2021.goodsmarketplace.service.shops;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsPublicDTO;

import java.util.List;

public interface ShopsService {

    ResponseEntity<?> saveShop(ShopsPublicDTO shopDTO);

    ResponseEntity<?> deleteShop(Long shopId);

    ResponseEntity<?> findAll(User activeUser);

    ResponseEntity<?> findById(User activeUser, Long shopId);

    ResponseEntity<?> changeInfo(User activeUser, ShopsPublicDTO shopDTO);
}
