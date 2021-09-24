package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProductData {

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();

        Product product = new Product();
        product.setId(1L);
        product.setName("iphone");
        product.setModel("12");
        product.setArticle("agggws");
        product.setDesc("I'm iphone");
        product.setWeight(130.0);
        product.setDimensions("10x10x10");
        product.setDateReleased(new GregorianCalendar());
        product.setDateCreated(new GregorianCalendar());
        product.setModifiedDate(new GregorianCalendar());
        product.setCategory(2L);
        product.setManufacturer(3L);

        list.add(product);

        return list;
    }

    public static ArrayList<ProductSaveDTO> getSaveDTO(){
        ArrayList<ProductSaveDTO> list = new ArrayList<>();

        list.add(ProductSaveDTO.builder()
                .id(1L)
                .categoryId(2L)
                .manufacturerId(3L)
                .name("iphone")
                .model("12")
                .article("agggws")
                .desc("I'm iphone")
                .weight(130.0)
                .dimensions("10x10x10")
                .dateReleased(new GregorianCalendar())
                .build());

        list.add(ProductSaveDTO.builder()
                .id(1L)
                .categoryId(2L)
                .manufacturerId(3L)
                .name("")
                .model("12")
                .article("agggws")
                .desc("I'm iphone")
                .weight(130.0)
                .dimensions("10x10x10")
                .dateReleased(new GregorianCalendar())
                .build());

        return list;
    }


}
