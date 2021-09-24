package ru.netcracker.studentsummer2021.goodsmarketplace.models;

public enum Status {
    CREATED("Создано"),
    PROCESSED("В обработке"),
    SHIPPED("Отгружено"),
    RECEIVED("Получено"),
    CANCELED("Отменено");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
