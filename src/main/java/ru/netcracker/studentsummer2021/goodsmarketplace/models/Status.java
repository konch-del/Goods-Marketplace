package ru.netcracker.studentsummer2021.goodsmarketplace.models;

public enum Status {
    Сreated("Создано"),
    Processed("В обработке"),
    Shipped("Отгружено"),
    Received("Получено"),
    Canceled("Отменено");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
