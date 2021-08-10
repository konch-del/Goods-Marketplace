package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic.FilterDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    @Query(value = "SELECT * " +
            "FROM characteristic " +
            "WHERE category_id IN " +
            "   (SELECT c.category_id " +
                "FROM category_ c " +
                "CONNECT BY PRIOR c.parent_id  = c.category_id " +
                "START WITH c.category_id = ?1)", nativeQuery = true)
    List<Characteristic> getByCategoryId(Long categoryId);

    @Query(value = "SELECT ch.CHARACTER_NAME, vc.VAL " +
            "FROM characteristic ch, VALUES_CHARACTER vc, PRODUCT_VALUES_CHARACTER pvc " +
            "WHERE vc.CHARACT_ID = ch.CHARACT_ID AND pvc.VC_ID = vc.VC_ID AND pvc.PRODUCT_ID = ?1", nativeQuery = true)
    List<?> getForProduct(Long productId);

    @Query(value = "SELECT p.PRODUCT_ID \"productId\", ch.CHARACTER_NAME \"charact\", vc.VAL \"value\"" +
            "FROM characteristic ch, VALUES_CHARACTER vc, PRODUCT_VALUES_CHARACTER pvc, PRODUCT p " +
            "WHERE pvc.VC_ID = vc.VC_ID AND vc.CHARACT_ID = ch.CHARACT_ID AND p.PRODUCT_ID = pvc.PRODUCT_ID " +
            "    AND ch.CHARACTER_NAME IN (?1)", nativeQuery = true)
    List<FilterDTO> getFilter(Set<String> param);
}
