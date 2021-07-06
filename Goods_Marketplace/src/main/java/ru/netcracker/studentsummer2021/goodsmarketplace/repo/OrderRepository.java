package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
