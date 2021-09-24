package ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter;


import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.LinkToProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.ValuesCharacterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ProductValuesCharacter;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;

@Component
public class ValuesCharacterConverter {

    public ValuesCharacterDTO fromValuesCharacterToDTO(ValuesCharacter valuesCharacter){
        return ValuesCharacterDTO.builder()
                .id(valuesCharacter.getId())
                .charact(valuesCharacter.getCharact())
                .value(valuesCharacter.getValue())
                .build();
    }

    public ValuesCharacter fromDTOToValuesCharacter(ValuesCharacterDTO valuesCharacterDTO){
        ValuesCharacter valuesCharacter = new ValuesCharacter();
        valuesCharacter.setCharact(valuesCharacterDTO.getCharact());
        valuesCharacter.setValue(valuesCharacterDTO.getValue());
        valuesCharacter.setId(valuesCharacterDTO.getId());
        return valuesCharacter;
    }

    public ProductValuesCharacter fromDTOToProductValuesCharacter(LinkToProductDTO linkToProductDTO){
        ProductValuesCharacter productValuesCharacter = new ProductValuesCharacter();
        productValuesCharacter.setProduct(linkToProductDTO.getProductId());
        productValuesCharacter.setValue(linkToProductDTO.getValueId());
        productValuesCharacter.setId(linkToProductDTO.getId());
        return productValuesCharacter;
    }
}
