package ru.netcracker.studentsummer2021.goodsmarketplace.service.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveShop(ShopsPublicDTO shopDTO) {
        if(shopDTO.getName().equals("") || shopDTO.getDesc().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shopRepository.save(shopsConverter.fromShopPublicToShop(shopDTO)), HttpStatus.CREATED);
    }

    /**
     * Удаляет магазин
     * @param shopId id магазина получений при запросе
     */
    @Override
    public ResponseEntity<?> deleteShop(Long shopId) {
        if(shopRepository.findById(shopId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shopRepository.deleteById(shopId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    /**
     * Находит все магазины
     * @param activeUser пользователб, отправивший запрос
     * @return полную информацию о магазинах для администраторов, нескрытую для пользователей
     */
    @Override
    public ResponseEntity<?> findAll(User activeUser) {
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(shopRepository.findAll()
                    .stream()
                    .map(shopsConverter::fromShopToShopAdmin)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(shopRepository.findAll()
                    .stream()
                    .map(shopsConverter::fromShopToShopPublic)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    /**
     * Поиск магазина по id
     * @param activeUser пользователь, вызывающий метод
     * @param shopId id магазина
     * @return полную информацию о магазине для администраторов, нескрытую для пользователей
     */
    @Override
    public ResponseEntity<?> findById(User activeUser, Long shopId) {
        if(shopRepository.findById(shopId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(shopsConverter.fromShopToShopAdmin(shopRepository.getById(shopId)), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(shopsConverter.fromShopToShopPublic(shopRepository.getById(shopId)), HttpStatus.OK);
        }
    }

    /**
     * Изменение информации о магазине
     * @param activeUser пользователь, вызывающий метод
     * @param shopDTO объект получений при запросе
     */
    @Override
    public ResponseEntity<?> changeInfo(User activeUser, ShopsPublicDTO shopDTO) {
        if(shopRepository.findById(shopDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        if(activeUser.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(shopRepository.save(shop), HttpStatus.OK);
        }else if(usersRepository.findByUsername(activeUser.getUsername()).getShopId().equals(shopDTO.getId())){
                return new ResponseEntity<>(shopRepository.save(shop), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
