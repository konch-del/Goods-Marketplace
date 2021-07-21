package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ManufacturerRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;


@Service("manufacturerServiceImpl")
public class ManufacturerServiceImpl implements ManufacturerService{

    @Value("${upload.path}")
    private String path;

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerConverter manufacturerConverter;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerConverter manufacturerConverter) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerConverter = manufacturerConverter;
    }

    @Override
    public void save(ManufacturerDTO manufacturerDTO) {
        manufacturerRepository.save(manufacturerConverter.fromDTOToManufacturer(manufacturerDTO));
    }

    @Override
    public ManufacturerDTO getById(Long manufacturerId) {
        return manufacturerConverter.fromManufacturerToDTO(manufacturerRepository.getById(manufacturerId));
    }

    @Override
    public List<ManufacturerDTO> getAll() {
        return manufacturerRepository.findAll()
                .stream()
                .map(manufacturerConverter::fromManufacturerToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void changeInfo(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = manufacturerRepository.getById(manufacturerDTO.getId());
        if(manufacturerDTO.getName() != null){
            manufacturer.setName(manufacturerDTO.getName());
        }
        if(manufacturerDTO.getDesc() != null){
            manufacturer.setDesc(manufacturerDTO.getDesc());
        }
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(Long manufacturerId) {
        manufacturerRepository.deleteById(manufacturerId);
    }

    @Override
    public String loadPicture(MultipartFile file, Long manufacturerId) throws IOException {
        File upload = new File(path);
        if(!upload.exists()){
            upload.mkdir();
        }
        String name = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        file.transferTo(new File(upload.getAbsolutePath() + "/" + name));
        manufacturerRepository.loadPicture(name, manufacturerId);
        return name;
    }

    @Override
    public void deletePicture(Long manufacturerId) {
        File upload = new File(path);
        File pic = new File(upload.getAbsolutePath() + "/" + manufacturerRepository.findById(manufacturerId).get().getPicture());
        pic.delete();
        manufacturerRepository.deletePicture(manufacturerId);
    }

    @Override
    public List<ManufacturerDTO> search(String name){
        return manufacturerRepository.search(name.toUpperCase(Locale.ROOT))
                .stream()
                .map(manufacturerConverter::fromManufacturerToDTO)
                .collect(Collectors.toList());
    };

    @Override
    public byte[] getPicture(Long manufacturerId) throws IOException {
        File upload = new File(path);
        File file = new File(upload.getAbsolutePath() + "/" + manufacturerRepository.findById(manufacturerId).get().getPicture());
        return Files.readAllBytes(file.toPath());
    }
}
