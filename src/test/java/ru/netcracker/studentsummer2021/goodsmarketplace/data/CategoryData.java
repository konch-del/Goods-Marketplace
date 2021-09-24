package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryWithHierarchy;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;

import java.util.ArrayList;

public class CategoryData {

    public static ArrayList<Category> getCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setParentID(4L);
        category.setName("abc");
        //Bad
        Category category2 = new Category();
        category2.setId(1L);
        category2.setParentID(2L);
        category2.setName("");
        //NotFound
        Category category3 = new Category();
        category3.setId(3L);
        category3.setParentID(2L);
        category3.setName("abc1");

        Category category4 = new Category();
        category4.setId(4L);
        //category4.setParentID(2L);
        category4.setName("qwerty");

        ArrayList<Category> list = new ArrayList<>();
        list.add(category);
        list.add(category2);
        list.add(category3);
        list.add(category4);

        return list;
    }

    public static CategoryWithHierarchy getCategoryWithHierarchy(){
        return CategoryWithHierarchy.builder()
                .id(1L)
                .name("abc")
                .parents(CategoryWithHierarchy.builder()
                        .id(4L)
                        .name("qwerty")
                        .build())
                .build();
    }
}
