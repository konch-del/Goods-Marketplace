package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer.ManufacturerService;
import java.io.IOException;

/**
 * Контроллер для REST-API производителя
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Value("${upload.path.manufacturer}")
    private String path;

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> save(@RequestBody ManufacturerDTO manufacturerDTO){
        return manufacturerService.save(manufacturerDTO);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id){
        return manufacturerService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return manufacturerService.getAll();
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeInfo(@RequestBody ManufacturerDTO manufacturerDTO){
        return manufacturerService.changeInfo(manufacturerDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return manufacturerService.delete(id);
    }

    @PostMapping("/loadPicture")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> loadPicture(@RequestParam("file") MultipartFile file, @RequestParam Long id) throws IOException {
        return manufacturerService.loadPicture(file, id);
    }

    @PostMapping("/deletePicture")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deletePicture(@RequestParam Long id){
        return manufacturerService.deletePicture(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String name){
        return manufacturerService.search(name);
    }

    @GetMapping(value = "/getPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<?> pic(@RequestParam Long id) throws IOException {
        return manufacturerService.getPicture(id);
    }

}
