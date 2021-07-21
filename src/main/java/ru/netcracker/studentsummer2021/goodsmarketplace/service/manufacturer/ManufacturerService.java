package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;

import java.io.IOException;
import java.util.List;

public interface ManufacturerService {

    void save(ManufacturerDTO manufacturerDTO);

    ManufacturerDTO getById(Long manufacturerId);

    List<ManufacturerDTO> getAll();

    void changeInfo(ManufacturerDTO manufacturerDTO);

    void delete(Long manufacturerId);

    String loadPicture(MultipartFile file, Long manufacturerId) throws IOException;

    void deletePicture(Long manufacturerId);

    List<ManufacturerDTO> search(String name);

    byte[] getPicture(Long manufacturerId) throws IOException;
}
