package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.product.ProductService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> save(@RequestBody ProductSaveDTO productPublicDTO){
        return productService.saveProduct(productPublicDTO);
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeInfo(@RequestBody ProductSaveDTO productPublicDTO){
        return productService.changeInfo(productPublicDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/getInCategory")
    public ResponseEntity<?> getInCategory(@RequestBody Map<String, String> charact){
        return productService.getProductInCategory(charact);
    }

    @GetMapping("/getInManufacturer")
    public ResponseEntity<?> getInManufacturer(@RequestBody Map<String, String> charact){
        return productService.getProductInManufacturer(charact);
    }

    @GetMapping("/getRecommended")
    public ResponseEntity<?> getRecommended(@AuthenticationPrincipal User user){
        return productService.getRecommended(user);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String search){
        return productService.search(search);
    }

    @GetMapping("/getRating")
    public ResponseEntity<?> getRating(@RequestParam Long id){
        return productService.getRating(id);
    }

    @PutMapping("/createPicture")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> createPicture(@RequestBody PictureProductDTO pictureProductDTO){
        return productService.createPicture(pictureProductDTO);
    }

    @PostMapping("/loadPicture")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> loadPicture(@RequestParam("file") MultipartFile file, @RequestParam Long id) throws IOException {
        return productService.loadPicture(file, id);
    }

    @GetMapping("/getAllPictures")
    public ResponseEntity<?> getAllPictures(@RequestParam Long id){
        return productService.getAllPicture(id);
    }

    @DeleteMapping("/deletePicture")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deletePicture(@RequestParam Long id){
        return productService.deletePicture(id);
    }

    @GetMapping(value = "/getPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<?> pic(@RequestParam Long id){
        return productService.getPicture(id);
    }

}
