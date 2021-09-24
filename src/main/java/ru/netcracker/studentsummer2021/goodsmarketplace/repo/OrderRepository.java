package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.* FROM order_ o, sales_unit su WHERE o.SU_ID = su.SU_ID AND su.SHOP_ID = ?1", nativeQuery = true)
    List<Order> findByShopId(Long shopId);

    @Query(value = "SELECT * FROM order_ WHERE USER_ID = ?1", nativeQuery = true)
    List<Order> findByUserId(Long userId);
}
