package ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic;

import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.CharacteristicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;

@Component
public class CharacteristicConverter {

    public CharacteristicDTO fromCharacteristicToDTO(Characteristic characteristic){
        return CharacteristicDTO.builder()
                .id(characteristic.getId())
                .name(characteristic.getName())
                .categoryId(characteristic.getCategoryId())
                .type(characteristic.getType())
                .build();
    }

    public Characteristic fromDTOToCharacteristic(CharacteristicDTO characteristicDTO){
        Characteristic characteristic = new Characteristic();
        characteristic.setId(characteristicDTO.getId());
        characteristic.setName(characteristicDTO.getName());
        characteristic.setCategoryId(characteristicDTO.getCategoryId());
        characteristic.setType(characteristicDTO.getType());
        return characteristic;
    }
}
