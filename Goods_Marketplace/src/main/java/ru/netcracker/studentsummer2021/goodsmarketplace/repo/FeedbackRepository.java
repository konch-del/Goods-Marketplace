package ru.netcracker.studentsummer2021.goodsmarketplace.repo;

import org.springframework.data.repository.CrudRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
}
