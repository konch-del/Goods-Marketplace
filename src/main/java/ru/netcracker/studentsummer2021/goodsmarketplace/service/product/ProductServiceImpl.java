package ru.netcracker.studentsummer2021.goodsmarketplace.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.category.CategoryConverter;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic.CharacteristicServiceImpl;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer.ManufacturerConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Value("${upload.path.product}")
    private String path;

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final PictureProductRepository pictureProduct;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final ManufacturerConverter manufacturerConverter;
    private final FeedbackRepository feedbackRepository;
    private final CharacteristicServiceImpl characteristicServiceImpl;
    private final UsersRepository usersRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter, PictureProductRepository pictureProduct, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository, CategoryConverter categoryConverter, ManufacturerConverter manufacturerConverter, FeedbackRepository feedbackRepository, CharacteristicServiceImpl characteristicServiceImpl, UsersRepository usersRepository) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.pictureProduct = pictureProduct;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
        this.manufacturerConverter = manufacturerConverter;
        this.feedbackRepository = feedbackRepository;
        this.characteristicServiceImpl = characteristicServiceImpl;
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductSaveDTO productPublicDTO) {
        if(productPublicDTO.getName().equals("") || productPublicDTO.getModel().equals("")
            || productPublicDTO.getArticle().equals("") || productPublicDTO.getDesc().equals("")
            || productPublicDTO.getWeight() == null || productPublicDTO.getDimensions().equals("")
            || productPublicDTO.getDateReleased() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productRepository.save(productConverter.fromPublicDTOToProduct(productPublicDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> changeInfo(ProductSaveDTO productPublicDTO) {
        if(productRepository.findById(productPublicDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.getById(productPublicDTO.getId());
        if(productPublicDTO.getName().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setName(productPublicDTO.getName());
        }
        if(productPublicDTO.getModel().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setModel(productPublicDTO.getModel());
        }
        if(productPublicDTO.getArticle().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setArticle(productPublicDTO.getArticle());
        }
        if(productPublicDTO.getDesc().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setDesc(productPublicDTO.getDesc());
        }
        if(productPublicDTO.getWeight() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setWeight(productPublicDTO.getWeight());
        }
        if(productPublicDTO.getDimensions().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setDimensions(productPublicDTO.getDimensions());
        }
        if(productPublicDTO.getDateReleased() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            product.setDateReleased(productPublicDTO.getDateReleased());
        }
        product.setManufacturer(productPublicDTO.getManufacturerId());
        product.setCategory(productPublicDTO.getCategoryId());
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getProduct(Long productId) {
        if (productRepository.findById(productId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.getById(productId);
        CategoryDTO categoryDTO = categoryConverter.fromCategoryToDTO(categoryRepository.getById(product.getCategory()));
        ManufacturerDTO manufacturerDTO = manufacturerConverter.fromManufacturerToDTO(manufacturerRepository.getById(product.getManufacturer()));
        PictureProduct picture = new PictureProduct();
        if(!pictureProduct.findByProductId(product.getId()).isEmpty()) {
            picture = pictureProduct.findByProductId(product.getId()).get(0);
        }else{
            picture = null;
        }
        return new ResponseEntity<>(productConverter.fromProductToPublicDTO(product, categoryDTO, manufacturerDTO, picture), HttpStatus.OK);
    }

    @Override
    public ProductPublicDTO getProductDTO(Long productId){
        Product product = productRepository.getById(productId);
        CategoryDTO categoryDTO = categoryConverter.fromCategoryToDTO(categoryRepository.getById(product.getCategory()));
        ManufacturerDTO manufacturerDTO = manufacturerConverter.fromManufacturerToDTO(manufacturerRepository.getById(product.getManufacturer()));
        PictureProduct picture = new PictureProduct();
        if(!pictureProduct.findByProductId(product.getId()).isEmpty()){
            picture = pictureProduct.findByProductId(product.getId()).get(0);
        }
        return productConverter.fromProductToPublicDTO(product, categoryDTO, manufacturerDTO, picture);
    }

    @Override
    public ResponseEntity<?> getProductInCategory(Map<String, String> charact) {
        String categoryId = charact.get("categoryId");
        charact.keySet().removeIf(x -> x.equals("categoryId"));
        if(charact.isEmpty()){
            return new ResponseEntity<>(productRepository.getAllProductInCategory(categoryId), HttpStatus.OK);
        }else {
            Set<Long> productsId = characteristicServiceImpl.getFilteredId(charact);
            return new ResponseEntity<>(productRepository.getProductInCategory(productsId, categoryId), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getProductInManufacturer(Map<String, String> charact) {
        String manufacturerId = charact.get("manufacturerId");
        charact.keySet().removeIf(x -> x.equals("manufacturerId"));
        if(charact.isEmpty()){
            return new ResponseEntity<>(productRepository.getAllProductInManufacturer(manufacturerId), HttpStatus.OK);
        }else {
            Set<Long> productsId = characteristicServiceImpl.getFilteredId(charact);
            return new ResponseEntity<>(productRepository.getProductInManufacturer(productsId, manufacturerId), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        if(productRepository.findById(productId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRecommended(User user) {
        Users users = usersRepository.findByUsername(user.getUsername());
        List<ProductPublicDTO> products = productRepository.getRecommended(users.getId())
                .stream()
                .map(s -> getProductDTO(s.getId()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> search(String search) {
        List<ProductPublicDTO> products = productRepository.search(search.toUpperCase(Locale.ROOT))
                .stream()
                .map(s -> getProductDTO(s.getId()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRating(Long productId) {
        return new ResponseEntity<>(feedbackRepository.getRating(productId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createPicture(PictureProductDTO pictureProductDTO) {
        if(pictureProductDTO.getProductId() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pictureProduct.save(productConverter.fromPictureDTOToPictureProduct(pictureProductDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> loadPicture(MultipartFile file, Long pictureId) throws IOException {
        if(pictureProduct.findById(pictureId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(file.isEmpty() || file.getSize() > 5200000){
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }
        File upload = new File(path);
        if(!upload.exists()){
            upload.mkdir();
        }
        String name = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        file.transferTo(new File(upload.getAbsolutePath() + "/" + name));
        pictureProduct.loadPicture(name, pictureId);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deletePicture(Long pictureId) {
        if(pictureProduct.findById(pictureId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        File upload = new File(path);
        File pic = new File(upload.getAbsolutePath() + "/" + pictureProduct.findById(pictureId).get().getPicture());
        if(pic.exists()) {
            pic.delete();
            pictureProduct.deleteById(pictureId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAllPicture(Long productId) {
        File upload = new File(path);
        if(pictureProduct.findByProductId(productId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Long> pictures = pictureProduct.findByProductId(productId).stream()
                .map((s) -> s.getId())
                .collect(Collectors.toList());
                //.stream()
                //.map((s) -> pictureProduct.getById(s.getId()).getPicture())
                /*.map((s) -> {
                    try {
                        return Files.readAllBytes(s.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new byte[0];
                })*/
                //.collect(Collectors.toList());
        return new ResponseEntity<>(pictures, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getPicture(Long pictureId) {
        File upload = new File(path);
        if(pictureProduct.findById(pictureId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        File file = new File(upload.getAbsolutePath() + "/" + pictureProduct.getById(pictureId).getPicture());
        try {
            return new ResponseEntity<>(Files.readAllBytes(file.toPath()), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
