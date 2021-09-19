package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.LinkToProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter.ValuesCharacterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter.ValuesCharacterService;

@RestController
@RequestMapping("/valuesCharacter")
public class ValuesCharacterController {

    private final ValuesCharacterService valuesCharacterService;

    public ValuesCharacterController(ValuesCharacterService valuesCharacterService) {
        this.valuesCharacterService = valuesCharacterService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> save(@RequestBody ValuesCharacterDTO valuesCharacterDTO){
        return valuesCharacterService.save(valuesCharacterDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id){
        return valuesCharacterService.get(id);
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeInfo(@RequestBody ValuesCharacterDTO valuesCharacterDTO){
        return valuesCharacterService.changeInfo(valuesCharacterDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return valuesCharacterService.delete(id);
    }

    @PostMapping("/linkToProduct")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> linkToProduct(@RequestBody LinkToProductDTO linkToProductDTO){
        return valuesCharacterService.linkToProduct(linkToProductDTO);
    }

    @GetMapping("/getForCharacter")
    public ResponseEntity<?> getForCharacter(@RequestParam Long id){
        return valuesCharacterService.getForCharacter(id);
    }
}
