package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
