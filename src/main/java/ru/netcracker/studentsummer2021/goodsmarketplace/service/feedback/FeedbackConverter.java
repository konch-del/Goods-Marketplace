package ru.netcracker.studentsummer2021.goodsmarketplace.service.feedback;


import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.feedback.FeedbackDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;

import java.util.GregorianCalendar;

@Component
public class FeedbackConverter {

    public Feedback fromDTOToFeedback(FeedbackDTO feedbackDTO){
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDTO.getId());
        feedback.setOrderId(feedbackDTO.getOrderId());
        feedback.setProductId(feedbackDTO.getProductId());
        feedback.setUserId(feedbackDTO.getUserId());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setText(feedbackDTO.getText());
        feedback.setVisibility(1);
        feedback.setDateCreated(new GregorianCalendar());
        return feedback;
    }

    public FeedbackDTO fromFeedbackToDTO(Feedback feedback){
        return FeedbackDTO.builder()
                .id(feedback.getId())
                .orderId(feedback.getOrderId())
                .productId(feedback.getProductId())
                .userId(feedback.getUserId())
                .rating(feedback.getRating())
                .text(feedback.getText())
                .visibility(feedback.isVisibility())
                .dateCreated(feedback.getDateCreated())
                .build();
    }
}
