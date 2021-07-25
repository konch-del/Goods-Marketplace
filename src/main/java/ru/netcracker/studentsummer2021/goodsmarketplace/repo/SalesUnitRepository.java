package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;

import java.util.List;

public interface SalesUnitRepository extends JpaRepository<SalesUnit, Long> {

    List<SalesUnit> findSalesUnitByShop(Long shopId);
}
