package ru.netcracker.studentsummer2021.goodsmarketplace.service.product;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;

import java.io.IOException;
import java.util.Map;

public interface ProductService {

    ResponseEntity<?> saveProduct(ProductSaveDTO productPublicDTO);

    ResponseEntity<?> changeInfo(ProductSaveDTO productPublicDTO);

    ResponseEntity<?> getProduct(Long productId);

    ProductPublicDTO getProductDTO(Long productId);

    ResponseEntity<?> getProductInCategory(Map<String, String> charact);

    ResponseEntity<?> getProductInManufacturer(Map<String, String> charact);

    ResponseEntity<?> deleteProduct(Long productId);

    ResponseEntity<?> getRecommended(User user);

    ResponseEntity<?> search(String search);

    ResponseEntity<?> getRating(Long productId);

    ResponseEntity<?> createPicture(PictureProductDTO pictureProductDTO);

    ResponseEntity<?> loadPicture(MultipartFile file, Long pictureId) throws IOException;

    ResponseEntity<?> deletePicture(Long pictureId);

    ResponseEntity<?> getAllPicture(Long productId);

    ResponseEntity<?> getPicture(Long pictureId);
}
