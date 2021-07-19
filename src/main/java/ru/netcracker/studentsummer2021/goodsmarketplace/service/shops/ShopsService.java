package ru.netcracker.studentsummer2021.goodsmarketplace.service.shops;

import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsPublicDTO;

import java.util.List;

public interface ShopsService {

    void saveShop(ShopsPublicDTO shopDTO);

    void deleteShop(Long shopId);

    List<ShopsDTO> findAll(User activeUser);

    ShopsDTO findById(User activeUser, Long shopId);

    void changeInfo(User activeUser, ShopsPublicDTO shopDTO);
}
