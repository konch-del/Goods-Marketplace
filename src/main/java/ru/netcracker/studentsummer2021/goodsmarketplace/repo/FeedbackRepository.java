package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE feedback_ SET VISIBILITY = ?2 WHERE FEEDBACK_ID = ?1", nativeQuery = true)
    void changeVisibility(Long feedbackId, Integer visibility);

    @Query(value = "SELECT * FROM FEEDBACK_ WHERE VISIBILITY = 1", nativeQuery = true)
    List<Feedback> getAllHidden();

    @Query(value = "SELECT * FROM FEEDBACK_ WHERE PRODUCT_ID = ?1", nativeQuery = true)
    List<Feedback> getAllForProduct(Long productId);

    @Query(value = "SELECT * FROM FEEDBACK_ WHERE USER_ID = ?1", nativeQuery = true)
    List<Feedback> getAllForUser(Long productId);

    @Query(value = "SELECT AVG(rating) FROM FEEDBACK_ WHERE product_id = ?1", nativeQuery = true)
    Double getRating(Long productId);
}
