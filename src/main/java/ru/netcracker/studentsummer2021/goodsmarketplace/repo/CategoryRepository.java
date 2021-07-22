package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT MAX(SUBSTR(SYS_CONNECT_BY_PATH(c.category_name, '->'),3)) " +
            "FROM category_ c " +
            "CONNECT BY PRIOR c.parent_id  = c.category_id " +
            "START WITH c.category_id = ?1 " +
            "ORDER BY ROWNUM DESC", nativeQuery = true)
    String getHierarchy(Long id);
}
