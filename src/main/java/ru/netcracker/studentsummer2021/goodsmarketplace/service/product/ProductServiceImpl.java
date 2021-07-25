package ru.netcracker.studentsummer2021.goodsmarketplace.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.PictureProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Value("${upload.path.product}")
    private String path;

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final PictureProductRepository pictureProduct;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter, PictureProductRepository pictureProduct) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.pictureProduct = pictureProduct;
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductPublicDTO productPublicDTO) {
        if(productPublicDTO.getName().equals("") || productPublicDTO.getModel().equals("")
            || productPublicDTO.getArticle().equals("") || productPublicDTO.getDesc().equals("")
            || productPublicDTO.getWeight() == null || productPublicDTO.getDimensions().equals("")
            || productPublicDTO.getDateReleased() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productRepository.save(productConverter.fromPublicDTOToProduct(productPublicDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> changeInfo(ProductPublicDTO productPublicDTO) {
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
        return null;
    }

    @Override
    public ResponseEntity<?> getProductInCategory() {
        return null;
    }

    @Override
    public ResponseEntity<?> getProductInManufacturer() {
        return null;
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
    public ResponseEntity<?> getRecommended() {
        return null;
    }

    @Override
    public ResponseEntity<?> search() {
        return null;
    }

    @Override
    public ResponseEntity<?> getRating() {
        return null;
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
        List<String> pictures = pictureProduct.findByProductId(productId).stream()
                .map((s) -> pictureProduct.getById(s.getId()).getPicture())
                /*.map((s) -> {
                    try {
                        return Files.readAllBytes(s.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new byte[0];
                })*/
                .collect(Collectors.toList());
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
