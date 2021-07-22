package ru.netcracker.studentsummer2021.goodsmarketplace.service.category;

import org.springframework.http.ResponseEntity;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;

public interface CategoryService {

    ResponseEntity<?> save(CategoryDTO categoryDTO);

    ResponseEntity<?> get(Long categoryId);

    ResponseEntity<?> changeInfo(CategoryDTO categoryDTO);

    ResponseEntity<?> delete(Long categoryId);
}
