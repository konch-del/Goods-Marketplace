package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.category.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return categoryService.delete(id);
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeInfo(@RequestBody CategoryDTO categoryDTO){
        return categoryService.changeInfo(categoryDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id){
        return categoryService.get(id);
    }

}
