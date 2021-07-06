package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Long> {
}
