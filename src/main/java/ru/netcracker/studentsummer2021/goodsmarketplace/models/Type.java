package ru.netcracker.studentsummer2021.goodsmarketplace.models;

public enum Type {
    STRING("[a-zA-Zа-яА-я]+"),
    NUMBER("\\d+"),
    BOOLEAN("[10]+");

    private final String type;

    Type(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
