package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SalesUnitData {

    public static ArrayList<SalesUnit> getSalesUnit(){
        ArrayList<SalesUnit> list = new ArrayList<>();

        SalesUnit salesUnit = new SalesUnit();
        salesUnit.setId(1L);
        salesUnit.setQuantity(5);
        salesUnit.setPrice(1000000);
        salesUnit.setShipCost(20);
        salesUnit.setDesc("new");
        salesUnit.setDateCreated(new GregorianCalendar());
        salesUnit.setModifiedDate(new GregorianCalendar());
        salesUnit.setShop(2L);
        salesUnit.setProduct(3L);
        //Bad
        SalesUnit salesUnit2 = new SalesUnit();
        salesUnit2.setId(1L);
        salesUnit2.setQuantity(5);
        salesUnit2.setPrice(-1000000);
        salesUnit2.setShipCost(20);
        salesUnit2.setDesc("new");
        salesUnit2.setDateCreated(new GregorianCalendar());
        salesUnit2.setModifiedDate(new GregorianCalendar());
        salesUnit2.setShop(2L);
        salesUnit2.setProduct(3L);

        SalesUnit salesUnit3 = new SalesUnit();
        salesUnit3.setId(1L);
        salesUnit3.setQuantity(5);
        salesUnit3.setPrice(1000000);
        salesUnit3.setShipCost(20);
        salesUnit3.setDesc("new");
        salesUnit3.setDateCreated(new GregorianCalendar());
        salesUnit3.setModifiedDate(new GregorianCalendar());
        salesUnit3.setShop(2L);
        salesUnit3.setProduct(3L);


        list.add(salesUnit);
        list.add(salesUnit2);
        list.add(salesUnit3);
        return list;
    }
}
