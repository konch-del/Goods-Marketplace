package ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic;

import org.springframework.http.ResponseEntity;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.CharacteristicDTO;

import java.util.Map;

public interface CharacteristicService {

    ResponseEntity<?> save(CharacteristicDTO characteristicDTO);

    ResponseEntity<?> get(Long characteristicId);

    ResponseEntity<?> getAll(Long categoryId);

    ResponseEntity<?> delete(Long characteristicId);

    ResponseEntity<?> getForProduct(Long productId);

    ResponseEntity<?> filter(Map<String, String> charact);

}
