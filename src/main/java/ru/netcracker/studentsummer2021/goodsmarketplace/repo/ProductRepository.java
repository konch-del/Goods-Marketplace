package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE product_id IN (?1) AND manufacturer_id = ?2", nativeQuery = true)
    List<Long> getProductInManufacturer(Set<Long> productsId, String manufacturerId);

    @Query(value = "SELECT * " +
            "FROM PRODUCT " +
            "WHERE PRODUCT_ID IN (" +
            "    SELECT p.PRODUCT_ID " +
            "    FROM PRODUCT p, ORDER_ o, SALES_UNIT su " +
            "    WHERE o.SU_ID = su.SU_ID AND su.PRODUCT_ID = p.PRODUCT_ID " +
            "        AND o.SU_ID IN (" +
            "            SELECT su.SU_ID " +
            "            FROM PRODUCT p, USERS_ u, ORDER_ o, SALES_UNIT su " +
            "            WHERE u.USER_ID = o.USER_ID AND o.SU_ID = su.SU_ID " +
            "              AND su.PRODUCT_ID = p.PRODUCT_ID AND u.USER_ID = ?1" +
            "            )" +
            "    )", nativeQuery = true)
    List<Product> getRecommended(Long userId);

    @Query(value = "SELECT * " +
            "FROM PRODUCT p " +
            "WHERE UPPER(p.PRODUCT_NAME || ' ' || p.ARTICLE || ' ' || p.PRODUCT_MODEL) LIKE %?1% ", nativeQuery = true)
    List<Product> search(String name);
}
