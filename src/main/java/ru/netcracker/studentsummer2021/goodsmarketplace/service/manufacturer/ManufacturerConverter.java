package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;

@Component
public class ManufacturerConverter {
    @Value("${upload.path}")
    private String path;

    public ManufacturerDTO fromManufacturerToDTO(Manufacturer manufacturer) {
        return ManufacturerDTO.builder()
                .id(manufacturer.getId())
                .desc(manufacturer.getDesc())
                .name(manufacturer.getName())
                .picture(manufacturer.getPicture())
                .build();
    }

    public Manufacturer fromDTOToManufacturer(ManufacturerDTO manufacturerDTO){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerDTO.getId());
        manufacturer.setDesc(manufacturerDTO.getDesc());
        manufacturer.setName(manufacturerDTO.getName());
        manufacturer.setPicture(manufacturerDTO.getPicture());
        return manufacturer;
    }
}
