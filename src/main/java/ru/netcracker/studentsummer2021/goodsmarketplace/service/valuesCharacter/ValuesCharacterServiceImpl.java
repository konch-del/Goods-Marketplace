package ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.LinkToProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.ValuesCharacterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductValuesCharacterRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ValuesCharacterRepository;

@Service("valuesCharacterServiceImpl")
public class ValuesCharacterServiceImpl implements ValuesCharacterService{

    private final ValuesCharacterRepository valuesCharacterRepository;
    private final ValuesCharacterConverter valuesCharacterConverter;
    private final ProductValuesCharacterRepository productValuesCharacterRepository;

    public ValuesCharacterServiceImpl(ValuesCharacterRepository valuesCharacterRepository, ValuesCharacterConverter valuesCharacterConverter, ProductValuesCharacterRepository productValuesCharacterRepository) {
        this.valuesCharacterRepository = valuesCharacterRepository;
        this.valuesCharacterConverter = valuesCharacterConverter;
        this.productValuesCharacterRepository = productValuesCharacterRepository;
    }

    @Override
    public ResponseEntity<?> save(ValuesCharacterDTO valuesCharacterDTO) {
        return new ResponseEntity<>(valuesCharacterRepository.save(valuesCharacterConverter.fromDTOToValuesCharacter(valuesCharacterDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> get(Long valuesCharacterId) {
        if(valuesCharacterRepository.findById(valuesCharacterId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(valuesCharacterConverter.fromValuesCharacterToDTO(valuesCharacterRepository.getById(valuesCharacterId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeInfo(ValuesCharacterDTO valuesCharacterDTO) {
        if(valuesCharacterRepository.findById(valuesCharacterDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ValuesCharacter valuesCharacter = valuesCharacterRepository.getById(valuesCharacterDTO.getId());
        valuesCharacter.setValue(valuesCharacterDTO.getValue());
        valuesCharacterRepository.save(valuesCharacter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long valuesCharacterId) {
        if(valuesCharacterRepository.findById(valuesCharacterId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        valuesCharacterRepository.deleteById(valuesCharacterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> linkToProduct(LinkToProductDTO linkToProductDTO) {
        if(linkToProductDTO.getProductId() == null || linkToProductDTO.getValueId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productValuesCharacterRepository.save(valuesCharacterConverter.fromDTOToProductValuesCharacter(linkToProductDTO)), HttpStatus.CREATED);
    }
}