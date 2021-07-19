package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {

    void save(ManufacturerDTO manufacturerDTO);

    ManufacturerDTO getById(Long manufacturerId);

    List<ManufacturerDTO> getAll();

    void changeInfo(ManufacturerDTO manufacturerDTO);

    void delete(Long manufacturerId);

    void loadPicture();

    void deletePicture();
}
