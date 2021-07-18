package ru.netcracker.studentsummer2021.goodsmarketplace.models;

public enum Permission {
    USER("user"),
    ADMINISTRATION("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}