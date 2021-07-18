package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ManufacturerRepository;

import java.util.List;


@Service("manufacturerServiceImpl")
public class ManufacturerServiceImpl implements ManufacturerService{

    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void save(ManufacturerDTO manufacturerDTO) {

    }

    @Override
    public ManufacturerDTO getById(Long manufacturerId) {
        return null;
    }

    @Override
    public List<ManufacturerDTO> getAll() {
        return null;
    }

    @Override
    public void changeInfo(ManufacturerDTO manufacturerDTO) {

    }

    @Override
    public void delete(Long manufacturerId) {

    }

    @Override
    public void loadPicture() {

    }

    @Override
    public void deletePicture() {

    }
}
