package ru.netcracker.studentsummer2021.goodsmarketplace.dto.category;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private Long parentId;

}
