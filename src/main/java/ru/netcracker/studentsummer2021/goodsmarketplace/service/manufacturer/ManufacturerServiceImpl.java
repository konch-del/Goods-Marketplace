package ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ManufacturerRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public ResponseEntity<?> save(ManufacturerDTO manufacturerDTO) {
        if(manufacturerDTO.getName().equals("") || manufacturerDTO.getDesc().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(manufacturerRepository.save(manufacturerConverter.fromDTOToManufacturer(manufacturerDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getById(Long manufacturerId) {
        if(manufacturerRepository.findById(manufacturerId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manufacturerConverter.fromManufacturerToDTO(manufacturerRepository.getById(manufacturerId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(manufacturerRepository.findAll()
                .stream()
                .map(manufacturerConverter::fromManufacturerToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeInfo(ManufacturerDTO manufacturerDTO) {
        if(manufacturerRepository.findById(manufacturerDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Manufacturer manufacturer = manufacturerRepository.getById(manufacturerDTO.getId());
        if(!manufacturerDTO.getName().equals("") && !manufacturerDTO.getDesc().equals("")){
            manufacturer.setName(manufacturerDTO.getName());
            manufacturer.setDesc(manufacturerDTO.getDesc());
            return new ResponseEntity<>(manufacturerRepository.save(manufacturer), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long manufacturerId) {
        if(manufacturerRepository.findById(manufacturerId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            manufacturerRepository.deleteById(manufacturerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> loadPicture(MultipartFile file, Long manufacturerId) throws IOException {
        if(manufacturerRepository.findById(manufacturerId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(file.isEmpty() || file.getSize() > 2200000){
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }
        File upload = new File(path);
        if(!upload.exists()){
            upload.mkdir();
        }
        String name = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        file.transferTo(new File(upload.getAbsolutePath() + "/" + name));
        manufacturerRepository.loadPicture(name, manufacturerId);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePicture(Long manufacturerId) {
        if(manufacturerRepository.findById(manufacturerId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        File upload = new File(path);
        File pic = new File(upload.getAbsolutePath() + "/" + manufacturerRepository.findById(manufacturerId).get().getPicture());
        if(pic.exists()) {
            pic.delete();
            manufacturerRepository.deletePicture(manufacturerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> search(String name){
        return new ResponseEntity<>(manufacturerRepository.search(name.toUpperCase(Locale.ROOT))
                .stream()
                .map(manufacturerConverter::fromManufacturerToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    };

    @Override
    public ResponseEntity<?> getPicture(Long manufacturerId){
        File upload = new File(path);
        if(manufacturerRepository.findById(manufacturerId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        File file = new File(upload.getAbsolutePath() + "/" + manufacturerRepository.findById(manufacturerId).get().getPicture());
        try {
            return new ResponseEntity<>(Files.readAllBytes(file.toPath()), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
