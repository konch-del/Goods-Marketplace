package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE manufacturer SET id_picture = ?1 WHERE manufacturer_id = ?2", nativeQuery = true)
    void loadPicture(String name, Long manufacturerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE manufacturer SET id_picture = null WHERE manufacturer_id = ?1", nativeQuery = true)
    void deletePicture(Long manufacturerId);

    @Query(value = "SELECT * FROM manufacturer WHERE UPPER(manufacturer_name) LIKE ?1%", nativeQuery = true)
    List<Manufacturer> search(String name);
}
