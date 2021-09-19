package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;

import java.util.List;

public interface ValuesCharacterRepository extends JpaRepository<ValuesCharacter, Long> {

    @Query(value = "SELECT * FROM VALUES_CHARACTER WHERE charact_id = ?1", nativeQuery = true)
    List<ValuesCharacter> getForCharacter(Long charactId);
}
