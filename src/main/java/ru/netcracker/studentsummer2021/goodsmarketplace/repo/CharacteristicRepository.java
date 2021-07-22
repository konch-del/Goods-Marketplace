package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    @Query(value = "SELECT * " +
            "FROM characteristic " +
            "WHERE category_id IN " +
            "   (SELECT c.category_id " +
                "FROM category_ c " +
                "CONNECT BY PRIOR c.parent_id  = c.category_id " +
                "START WITH c.category_id = ?1)", nativeQuery = true)
    List<Characteristic> getByCategoryId(Long categoryId);
}
