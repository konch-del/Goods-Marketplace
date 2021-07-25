package ru.netcracker.studentsummer2021.goodsmarketplace.service.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;

import java.io.IOException;

public interface ProductService {

    ResponseEntity<?> saveProduct(ProductPublicDTO productPublicDTO);

    ResponseEntity<?> changeInfo(ProductPublicDTO productPublicDTO);

    ResponseEntity<?> getProduct(Long productId);

    ResponseEntity<?> getProductInCategory();

    ResponseEntity<?> getProductInManufacturer();

    ResponseEntity<?> deleteProduct(Long productId);

    ResponseEntity<?> getRecommended();

    ResponseEntity<?> search();

    ResponseEntity<?> getRating();

    ResponseEntity<?> createPicture(PictureProductDTO pictureProductDTO);

    ResponseEntity<?> loadPicture(MultipartFile file, Long pictureId) throws IOException;

    ResponseEntity<?> deletePicture(Long pictureId);

    ResponseEntity<?> getAllPicture(Long productId);

    ResponseEntity<?> getPicture(Long pictureId);
}
