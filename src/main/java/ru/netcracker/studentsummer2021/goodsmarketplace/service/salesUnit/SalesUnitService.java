package ru.netcracker.studentsummer2021.goodsmarketplace.service.salesUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit.SalesUnitDTO;

public interface SalesUnitService {

    ResponseEntity<?> save(User user, SalesUnitDTO salesUnitDTO);

    ResponseEntity<?> get(Long salesUnitId);

    ResponseEntity<?> changeInfo(User user, SalesUnitDTO salesUnitDTO);

    ResponseEntity<?> delete(User user, Long salesUnitId);

    ResponseEntity<?> getForCity();

    ResponseEntity<?> getAll(User user, Long salesUnitId);

}
