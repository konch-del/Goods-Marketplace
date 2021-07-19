package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
