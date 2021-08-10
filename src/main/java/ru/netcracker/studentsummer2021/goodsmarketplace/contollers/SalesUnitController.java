package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.salesUnit.SalesUnitService;

@RestController
@RequestMapping("/salesUnit")
public class SalesUnitController {

    private final SalesUnitService salesUnitService;

    public SalesUnitController(SalesUnitService salesUnitService) {
        this.salesUnitService = salesUnitService;
    }

    @PutMapping("/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal User user, @RequestBody SalesUnitDTO salesUnitDTO){
        return salesUnitService.save(user, salesUnitDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @RequestParam Long id){
        return salesUnitService.delete(user, id);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id){
        return salesUnitService.get(id);
    }

    @PostMapping("/changeInfo")
    public ResponseEntity<?> changeInfo(@AuthenticationPrincipal User user, @RequestBody SalesUnitDTO salesUnitDTO){
        return salesUnitService.changeInfo(user, salesUnitDTO);
    }

    @GetMapping("/getForCity")
    public ResponseEntity<?> getForCity(@AuthenticationPrincipal User user, @RequestParam Long id){
        return salesUnitService.getForCity(user, id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal User user, @RequestParam Long id){
        return salesUnitService.getAll(user, id);
    }

}
