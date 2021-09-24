package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;

import java.io.IOException;
import java.util.List;

public interface ManufacturerService {

    ResponseEntity<?> save(ManufacturerDTO manufacturerDTO);

    ResponseEntity<?> getById(Long manufacturerId);

    ResponseEntity<?> getAll();

    ResponseEntity<?> changeInfo(ManufacturerDTO manufacturerDTO);

    ResponseEntity<?> delete(Long manufacturerId);

    ResponseEntity<?> loadPicture(MultipartFile file, Long manufacturerId) throws IOException;

    ResponseEntity<?> deletePicture(Long manufacturerId);

    ResponseEntity<?> search(String name);

    ResponseEntity<?> getPicture(Long manufacturerId) throws IOException;
}
