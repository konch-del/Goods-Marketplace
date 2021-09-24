package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;

import java.util.List;

public interface SalesUnitRepository extends JpaRepository<SalesUnit, Long> {

    List<SalesUnit> findSalesUnitByShop(Long shopId);

    @Query(value = "SELECT su.* " +
            "FROM USERS_ u, SALES_UNIT su, USERS_ u1, SHOP s " +
            "WHERE su.PRODUCT_ID = ?1 AND u.USER_ID = ?2" +
            "    AND s.SHOP_ID = su.SHOP_ID AND su.QUANTITY > 0 " +
            "    AND u1.SHOP_ID = s.SHOP_ID AND u1.CITY = u.CITY", nativeQuery = true)
    List<SalesUnit> getForCity(Long suId, Long userId);
}
