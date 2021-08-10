package ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.CharacteristicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.FilterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.FilterDTOImpl;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CharacteristicRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("characteristicServiceImpl")
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final CharacteristicConverter characteristicConverter;

    @Autowired
    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository, CharacteristicConverter characteristicConverter) {
        this.characteristicRepository = characteristicRepository;
        this.characteristicConverter = characteristicConverter;
    }

    @Override
    public ResponseEntity<?> save(CharacteristicDTO characteristicDTO) {
        if(characteristicDTO.getName().equals("") || characteristicDTO.getCategoryId() == null || characteristicDTO.getType().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(characteristicRepository.save(characteristicConverter.fromDTOToCharacteristic(characteristicDTO)) , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> get(Long characteristicId) {
        if(characteristicRepository.findById(characteristicId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characteristicRepository.getById(characteristicId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll(Long categoryId) {
        if(characteristicRepository.getByCategoryId(categoryId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characteristicRepository.getByCategoryId(categoryId)
                .stream()
                .map(characteristicConverter::fromCharacteristicToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long characteristicId) {
        if(characteristicRepository.findById(characteristicId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        characteristicRepository.deleteById(characteristicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getForProduct(Long productId) {
        List<?> result = characteristicRepository.getForProduct(productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> filter(Map<String, String> charact) {
        return new ResponseEntity<>(getFilteredId(charact), HttpStatus.OK);
    }

    public Set<Long> getFilteredId(Map<String, String> charact){
        Set<String> character = charact.keySet();
        return characteristicRepository.getFilter(character)
                .stream()
                .filter(x -> x.getValue().equals(charact.get(x.getCharact())))
                .map(FilterDTO::getProductId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() >= charact.size())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
