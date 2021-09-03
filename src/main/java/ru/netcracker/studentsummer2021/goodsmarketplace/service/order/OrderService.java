package ru.netcracker.studentsummer2021.goodsmarketplace.service.order;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.order.OrderPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Status;

public interface OrderService {

    ResponseEntity<?> save(User user, OrderPublicDTO orderPublicDTO);

    ResponseEntity<?> get(User user, Long orderId);

    ResponseEntity<?> getAll(User user, Long shopId);

    ResponseEntity<?> getForUser(User user, Long userId);

    ResponseEntity<?> changeInfo(User user, OrderPublicDTO orderPublicDTO);

    ResponseEntity<?> changeStatus(User user, Long orderId, Status status);

    ResponseEntity<?> delete(Long orderId);

}
