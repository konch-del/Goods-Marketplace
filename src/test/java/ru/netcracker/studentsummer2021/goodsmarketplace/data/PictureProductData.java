package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PictureProductData {

    public static ArrayList<PictureProductDTO> getPictures(){
        ArrayList<PictureProductDTO> list = new ArrayList<>();

        PictureProductDTO pictureProduct = new PictureProductDTO();
        pictureProduct.setId(1L);
        //pictureProduct.setPicture();
        pictureProduct.setDateCreated(new GregorianCalendar());
        pictureProduct.setProductId(2L);

        list.add(pictureProduct);
        return list;
    }

    public static ArrayList<PictureProduct> getPicturesP(){
        ArrayList<PictureProduct> list = new ArrayList<>();

        PictureProduct pictureProduct = new PictureProduct();
        pictureProduct.setId(1L);
        //pictureProduct.setPicture();
        pictureProduct.setDateCreated(new GregorianCalendar());
        pictureProduct.setProduct(2L);

        list.add(pictureProduct);
        return list;
    }
}
