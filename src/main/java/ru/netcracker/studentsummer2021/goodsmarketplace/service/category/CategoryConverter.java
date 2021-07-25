package ru.netcracker.studentsummer2021.goodsmarketplace.service.category;

import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryWithHierarchy;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CategoryRepository;

@Component
public class CategoryConverter {

    private final CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO fromCategoryToDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentID())
                .build();
    }

    public Category fromDTOToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setParentID(categoryDTO.getParentId());
        return category;
    }

    public CategoryWithHierarchy fromCategoryToHierarchyDTO(Category category){
        if(categoryRepository.getById(category.getId()).getParentID() != null){
            return CategoryWithHierarchy.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parents(fromCategoryToHierarchyDTO(categoryRepository.getHierarchy(category.getParentID())))
                    .build();
        }else {
            return CategoryWithHierarchy.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parents(null)
                    .build();
        }
    }
}
