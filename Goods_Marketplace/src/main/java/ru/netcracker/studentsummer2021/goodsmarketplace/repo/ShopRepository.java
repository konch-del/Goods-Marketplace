package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}
