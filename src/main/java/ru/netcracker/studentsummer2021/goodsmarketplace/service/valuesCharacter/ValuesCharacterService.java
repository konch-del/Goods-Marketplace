package ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter;

import org.springframework.http.ResponseEntity;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.LinkToProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.ValuesCharacterDTO;

public interface ValuesCharacterService {

    ResponseEntity<?> save(ValuesCharacterDTO valuesCharacterDTO);

    ResponseEntity<?> get(Long valuesCharacterId);

    ResponseEntity<?> changeInfo(ValuesCharacterDTO valuesCharacterDTO);

    ResponseEntity<?> delete(Long valuesCharacterId);

    ResponseEntity<?> linkToProduct(LinkToProductDTO linkToProductDTO);
}
