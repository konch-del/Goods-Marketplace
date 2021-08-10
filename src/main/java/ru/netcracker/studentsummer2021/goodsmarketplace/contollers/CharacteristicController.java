package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.CharacteristicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic.CharacteristicService;

import java.util.Map;

@RestController
@RequestMapping("/characteristic")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> save(@RequestBody CharacteristicDTO characteristicDTO){
        return characteristicService.save(characteristicDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return characteristicService.delete(id);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id){
        return characteristicService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam Long id){
        return characteristicService.getAll(id);
    }

    @GetMapping("/getForProduct")
    public ResponseEntity<?> getForProduct(@RequestParam Long id){
        return characteristicService.getForProduct(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody Map<String, String> charact) {
        return characteristicService.filter(charact);
    }

}
