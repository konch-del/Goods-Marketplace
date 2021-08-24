package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;

import java.util.ArrayList;

public class ManufacturerData {

    public static ArrayList<Manufacturer> getManufacturers(){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("farm");
        manufacturer.setDesc("It's a farm");
        //manufacturer.setPicture();

        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setId(2L);
        manufacturer2.setName("");
        manufacturer2.setDesc("It's a farm");
        //manufacturer.setPicture();

        ArrayList<Manufacturer> list = new ArrayList<>();
        list.add(manufacturer);
        list.add(manufacturer2);

        return list;
    }
}
