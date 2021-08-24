package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;

import java.util.ArrayList;

public class ValuesCharactData {

    public static ArrayList<ValuesCharacter> getData(){
        ArrayList<ValuesCharacter> list = new ArrayList<>();

        ValuesCharacter valuesCharacter = new ValuesCharacter();
        valuesCharacter.setId(1L);
        valuesCharacter.setValue("1");
        valuesCharacter.setCharact(1L);
        //Bad
        ValuesCharacter valuesCharacter2 = new ValuesCharacter();
        valuesCharacter2.setId(1L);
        valuesCharacter2.setValue("3");
        valuesCharacter2.setCharact(1L);

        ValuesCharacter valuesCharacter3 = new ValuesCharacter();
        valuesCharacter3.setId(1L);
        valuesCharacter3.setValue("0");
        valuesCharacter3.setCharact(1L);

        list.add(valuesCharacter);
        list.add(valuesCharacter2);
        list.add(valuesCharacter3);
        return list;
    }
}
