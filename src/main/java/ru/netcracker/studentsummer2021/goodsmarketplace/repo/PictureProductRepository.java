package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;

import java.util.List;

public interface PictureProductRepository extends JpaRepository<PictureProduct, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE PICTURE_PRODUCT SET PICTURE_ID = ?1 WHERE PP_ID = ?2", nativeQuery = true)
    void loadPicture(String name, Long pictureId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PICTURE_PRODUCT SET PICTURE_ID = null WHERE PP_ID = ?1", nativeQuery = true)
    void deletePicture(Long pictureId);

    @Query(value = "SELECT * FROM PICTURE_PRODUCT WHERE PRODUCT_ID = ?1", nativeQuery = true)
    List<PictureProduct> findByProductId(Long productId);
}
