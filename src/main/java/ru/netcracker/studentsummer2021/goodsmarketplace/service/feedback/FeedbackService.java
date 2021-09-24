package ru.netcracker.studentsummer2021.goodsmarketplace.service.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.feedback.FeedbackDTO;

public interface FeedbackService {

    ResponseEntity<?> save(@AuthenticationPrincipal User user, FeedbackDTO feedbackDTO);

    ResponseEntity<?> get(@AuthenticationPrincipal User user, Long feedbackId);

    ResponseEntity<?> changeInfo(User user, FeedbackDTO feedbackDTO);

    ResponseEntity<?> changeVisibility(Long feedbackId, Integer visibility);

    ResponseEntity<?> getAllHidden();

    ResponseEntity<?> getAllForProduct(User user, Long productId);

    ResponseEntity<?> getAllForUser(User user, Long userId);

    ResponseEntity<?> delete(User user, Long feedbackId);
}
