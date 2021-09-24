package ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.LinkToProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.ValuesCharacterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CharacteristicRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductValuesCharacterRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ValuesCharacterRepository;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("valuesCharacterServiceImpl")
public class ValuesCharacterServiceImpl implements ValuesCharacterService{

    private final ValuesCharacterRepository valuesCharacterRepository;
    private final ValuesCharacterConverter valuesCharacterConverter;
    private final ProductValuesCharacterRepository productValuesCharacterRepository;
    private final CharacteristicRepository characteristicRepository;

    public ValuesCharacterServiceImpl(ValuesCharacterRepository valuesCharacterRepository, ValuesCharacterConverter valuesCharacterConverter, ProductValuesCharacterRepository productValuesCharacterRepository, CharacteristicRepository characteristicRepository) {
        this.valuesCharacterRepository = valuesCharacterRepository;
        this.valuesCharacterConverter = valuesCharacterConverter;
        this.productValuesCharacterRepository = productValuesCharacterRepository;
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public ResponseEntity<?> save(ValuesCharacterDTO valuesCharacterDTO) {
        Characteristic characteristic = characteristicRepository.getById(valuesCharacterDTO.getCharact());
        Pattern p = Pattern.compile(characteristic.getType().getType());
        if(valuesCharacterDTO.getValue().matches(characteristic.getType().getType())){
            return new ResponseEntity<>(valuesCharacterRepository.save(valuesCharacterConverter.fromDTOToValuesCharacter(valuesCharacterDTO)), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @Override
    public ResponseEntity<?> getForCharacter(Long charactId) {
        return new ResponseEntity<>(
                valuesCharacterRepository.getForCharacter(charactId).stream()
                .map(valuesCharacterConverter::fromValuesCharacterToDTO)
                .collect(Collectors.toList())
                ,HttpStatus.OK);
    }
}
