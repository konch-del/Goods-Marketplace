package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer.ManufacturerService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * Контроллер для REST-API производителя
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Value("${upload.path}")
    private String path;

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public void save(@RequestBody ManufacturerDTO manufacturerDTO){
        manufacturerService.save(manufacturerDTO);
    }

    @GetMapping("/getById")
    public ManufacturerDTO getById(@RequestParam Long id){
        return manufacturerService.getById(id);
    }

    @GetMapping("/getAll")
    public List<ManufacturerDTO> getAll(){
        return manufacturerService.getAll();
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAuthority('admin')")
    public void changeInfo(@RequestBody ManufacturerDTO manufacturerDTO){
        manufacturerService.changeInfo(manufacturerDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@RequestParam Long id){
        manufacturerService.delete(id);
    }

    @PostMapping("/loadPicture")
    @PreAuthorize("hasAuthority('admin')")
    public String loadPicture(@RequestParam("file") MultipartFile file, @RequestParam Long id) throws IOException {
        return manufacturerService.loadPicture(file, id);
    }

    @PostMapping("/deletePicture")
    @PreAuthorize("hasAuthority('admin')")
    public void deletePicture(@RequestParam Long id){
        manufacturerService.deletePicture(id);
    }

    @GetMapping("/search")
    public List<ManufacturerDTO> search(@RequestParam String name){
        return manufacturerService.search(name);
    }

    @GetMapping(value = "/getPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] pic(@RequestParam Long id) throws IOException {
        return manufacturerService.getPicture(id);
    }

}
