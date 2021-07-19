package ru.netcracker.studentsummer2021.goodsmarketplace.service.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops.ShopsPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Shop;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ShopRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализуюющий бизнес-логику
 */
@Service("shopsServiceImpl")
public class ShopsServiceImpl implements ShopsService {

    private final ShopRepository shopRepository;
    private final UsersRepository usersRepository;
    private final ShopsConverter shopsConverter;

    @Autowired
    public ShopsServiceImpl(ShopRepository shopRepository, UsersRepository usersRepository, ShopsConverter shopsConverter) {
        this.shopRepository = shopRepository;
        this.usersRepository = usersRepository;
        this.shopsConverter = shopsConverter;
    }

    /**
     * Сохраняет магазин в БД
     * @param shopDTO объект получений при запросе
     */
    @Override
    public void saveShop(ShopsPublicDTO shopDTO) {
        shopRepository.save(shopsConverter.fromShopPublicToShop(shopDTO));
    }

    /**
     * Удаляет магазин
     * @param shopId id магазина получений при запросе
     */
    @Override
    public void deleteShop(Long shopId) {
        shopRepository.deleteById(shopId);
    }

    /**
     * Находит все магазины
     * @param activeUser пользователб, отправивший запрос
     * @return полную информацию о магазинах для администраторов, нескрытую для пользователей
     */
    @Override
    public List<ShopsDTO> findAll(User activeUser) {
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return shopRepository.findAll()
                    .stream()
                    .map(shopsConverter::fromShopToShopAdmin)
                    .collect(Collectors.toList());
        }else{
            return shopRepository.findAll()
                    .stream()
                    .map(shopsConverter::fromShopToShopPublic)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Поиск магазина по id
     * @param activeUser пользователь, вызывающий метод
     * @param shopId id магазина
     * @return полную информацию о магазине для администраторов, нескрытую для пользователей
     */
    @Override
    public ShopsDTO findById(User activeUser, Long shopId) {
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return shopsConverter.fromShopToShopAdmin(shopRepository.getById(shopId));
        }else {
            return shopsConverter.fromShopToShopPublic(shopRepository.getById(shopId));
        }
    }

    /**
     * Изменение информации о магазине
     * @param activeUser пользователь, вызывающий метод
     * @param shopDTO объект получений при запросе
     */
    @Override
    public void changeInfo(User activeUser, ShopsPublicDTO shopDTO) {
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            Shop shop = shopRepository.getById(shopDTO.getId());
            if(shopDTO.getEmail() != null) {
                shop.setEmail(shopDTO.getEmail());
            }
            if(shopDTO.getPhoneNumber() != null) {
                shop.setPhoneNumber(shopDTO.getPhoneNumber());
            }
            if(shopDTO.getWorkTime() != null) {
                shop.setWorkTime(shopDTO.getWorkTime());
            }
            if(shopDTO.getAddress() != null) {
                shop.setAddress(shopDTO.getAddress());
            }
            shopRepository.save(shop);
        }else if(usersRepository.findByUsername(activeUser.getUsername()).getShopId().equals(shopDTO.getId())){
                Shop shop = shopRepository.getById(shopDTO.getId());
                if(shopDTO.getEmail() != null) {
                    shop.setEmail(shopDTO.getEmail());
                }
                if(shopDTO.getPhoneNumber() != null) {
                    shop.setPhoneNumber(shopDTO.getPhoneNumber());
                }
                if(shopDTO.getWorkTime() != null) {
                    shop.setWorkTime(shopDTO.getWorkTime());
                }
                if(shopDTO.getAddress() != null) {
                    shop.setAddress(shopDTO.getAddress());
                }
                shopRepository.save(shop);
            }
    }
}
