package ru.netcracker.studentsummer2021.goodsmarketplace.service.shops;


import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Shop;

import java.util.GregorianCalendar;

/**
 * Класс конвертертатор между пользовательскими отображениями и отображениями в БД
 */
@Component
public class ShopsConverter {

    /**
     * Конвертирует ShopsPublicDTO в экземпляр Shop
     * @param shopDTO DTO-экземпляр
     * @return Shop экземпляр
     */
    public Shop fromShopPublicToShop(ShopsPublicDTO shopDTO){
        Shop shop = new Shop();
        shop.setId(shop.getId());
        shop.setName(shopDTO.getName());
        shop.setDesc(shopDTO.getDesc());
        shop.setEmail(shopDTO.getEmail());
        shop.setPhoneNumber(shopDTO.getPhoneNumber());
        shop.setAddress(shopDTO.getAddress());
        shop.setPhoneNumber(shopDTO.getPhoneNumber());
        shop.setDateCreated(new GregorianCalendar());
        shop.setModifiedDate(new GregorianCalendar());
        return shop;
    }

    /**
     *
     * @param shopDTO
     * @return
     */
    public Shop fromShopAdminToShop(ShopsAdminDTO shopDTO){
        return null;
    }

    /**
     * Конвертирует Shop в ShopsPublicDTO
     * @param shop экземпляр
     * @return ShopsPublicDTO экземпляр
     */
    public ShopsPublicDTO fromShopToShopPublic(Shop shop){
       return ShopsPublicDTO.builder()
               .id(shop.getId())
               .name(shop.getName())
               .desc(shop.getDesc())
               .email(shop.getEmail())
               .phoneNumber(shop.getPhoneNumber())
               .address(shop.getAddress())
               .workTime(shop.getWorkTime())
               .build();
    }

    /**
     * Конвертирует Shop в ShopsAdminDTO
     * @param shop экземпляр
     * @return ShopsAdminDTO экземпляр
     */
    public ShopsAdminDTO fromShopToShopAdmin(Shop shop){
        return ShopsAdminDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .desc(shop.getDesc())
                .email(shop.getEmail())
                .phoneNumber(shop.getPhoneNumber())
                .address(shop.getAddress())
                .workTime(shop.getWorkTime())
                .dateCreated(shop.getDateCreated())
                .modifiedDate(shop.getModifiedDate())
                .build();
    }
}
