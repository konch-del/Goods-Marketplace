package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Type;

import java.util.ArrayList;

public class CharacteristicData {

    public static ArrayList<Characteristic> getCharacteristic(){
        ArrayList<Characteristic> list = new ArrayList<>();

        Characteristic characteristic = new Characteristic();
        characteristic.setId(1L);
        characteristic.setName("abc");
        characteristic.setType(Type.BOOLEAN);
        characteristic.setCategoryId(12L);

        Characteristic characteristic2 = new Characteristic();
        characteristic2.setId(2L);
        characteristic2.setName("qwerty");
        characteristic2.setType(Type.BOOLEAN);
        characteristic2.setCategoryId(12L);


        list.add(characteristic);
        list.add(characteristic2);
        return list;
    }
}
