package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long userId);

    @Query(value = "SELECT * FROM users_ WHERE USERNAME = ?1 OR PHONE_NUMBER = ?1 OR EMAIL = ?1", nativeQuery = true)
    Users findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users_ SET activation = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeActiv(Long userId, Integer activ);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users_ SET email = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeEmail(Long userId, String newEmail);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users_ SET pass = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changePass(Long userId, String pass);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users_ SET shop_id = ?2 WHERE user_id = ?1", nativeQuery = true)
    void changeShop(Long userId, Long shopId);
}
