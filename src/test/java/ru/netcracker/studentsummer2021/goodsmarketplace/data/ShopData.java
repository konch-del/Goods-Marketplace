package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Shop;

import java.util.GregorianCalendar;

public class ShopData {

    public static Shop getShop(){
        Shop shop = new Shop();

        shop.setAddress("rhb rsbr1");
        shop.setPhoneNumber("8005553535");
        shop.setEmail("abc1@abc.com");
        shop.setId(1L);
        shop.setWorkTime("9:00-15:00");
        shop.setModifiedDate(new GregorianCalendar());
        shop.setDateCreated(new GregorianCalendar());
        shop.setDesc("I am abc");
        shop.setName("abc");

        return shop;
    }
}
