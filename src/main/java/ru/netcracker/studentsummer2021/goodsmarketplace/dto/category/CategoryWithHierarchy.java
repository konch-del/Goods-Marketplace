package ru.netcracker.studentsummer2021.goodsmarketplace.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;

@Getter
@Setter
@Builder
public class CategoryWithHierarchy {

    private Long id;
    private String name;
    private CategoryWithHierarchy parents;


}
