package ru.netcracker.studentsummer2021.goodsmarketplace.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CategoryRepository;


@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }


    @Override
    public ResponseEntity<?> save(CategoryDTO categoryDTO) {
        if(categoryDTO.getName().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryRepository.save(categoryConverter.fromDTOToCategory(categoryDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> get(Long categoryId) {
        if(categoryRepository.findById(categoryId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryConverter.fromCategoryToHierarchyDTO(categoryRepository.findById(categoryId).get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> changeInfo(CategoryDTO categoryDTO) {
        if(categoryRepository.findById(categoryDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.findById(categoryDTO.getId()).get();
        if(categoryDTO.getName().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        category.setName(categoryDTO.getName());
        category.setParentID(categoryDTO.getParentId());
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long categoryId) {
        if(categoryRepository.findById(categoryId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
