package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
