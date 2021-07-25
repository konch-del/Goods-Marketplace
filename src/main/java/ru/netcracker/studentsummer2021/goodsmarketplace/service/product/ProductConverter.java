package ru.netcracker.studentsummer2021.goodsmarketplace.service.product;

import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;

import java.util.GregorianCalendar;


@Component
public class ProductConverter {

    public Product fromPublicDTOToProduct(ProductPublicDTO productPublicDTO){
        Product product = new Product();
        product.setId(productPublicDTO.getId());
        product.setName(productPublicDTO.getName());
        product.setModel(productPublicDTO.getModel());
        product.setArticle(productPublicDTO.getArticle());
        product.setDesc(productPublicDTO.getDesc());
        product.setWeight(productPublicDTO.getWeight());
        product.setDimensions(productPublicDTO.getDimensions());
        product.setDateReleased(productPublicDTO.getDateReleased());
        product.setDateCreated(new GregorianCalendar());
        product.setModifiedDate(new GregorianCalendar());
        product.setCategory(productPublicDTO.getCategoryId());
        product.setManufacturer(productPublicDTO.getManufacturerId());
        return product;
    }

    public ProductPublicDTO fromProductToPublicDTO(Product product){
        return ProductPublicDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .model(product.getModel())
                .article(product.getArticle())
                .desc(product.getDesc())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .dateReleased(product.getDateReleased())
                .categoryId(product.getCategory())
                .manufacturerId(product.getManufacturer())
                .build();
    }

    public ProductAdminDTO fromProductToAdminDTO(Product product){
        return ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .model(product.getModel())
                .article(product.getArticle())
                .desc(product.getDesc())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .dateReleased(product.getDateReleased())
                .categoryId(product.getCategory())
                .manufacturerId(product.getManufacturer())
                .dateCreation(product.getDateCreated())
                .modifiedDate(product.getModifiedDate())
                .build();
    }

    public PictureProduct fromPictureDTOToPictureProduct(PictureProductDTO pictureProductDTO){
        PictureProduct pictureProduct = new PictureProduct();
        pictureProduct.setId(pictureProductDTO.getId());
        pictureProduct.setProduct(pictureProductDTO.getProductId());
        pictureProduct.setDateCreated(new GregorianCalendar());
        return pictureProduct;
    }
}
