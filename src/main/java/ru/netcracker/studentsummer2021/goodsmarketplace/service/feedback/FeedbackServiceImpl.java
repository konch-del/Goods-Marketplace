package ru.netcracker.studentsummer2021.goodsmarketplace.service.feedback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.feedback.FeedbackDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.FeedbackRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;

import java.util.List;

@Service("feedbackServiceImpl")
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;
    private final UsersRepository usersRepository;
    private final FeedbackConverter feedbackConverter;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UsersRepository usersRepository, FeedbackConverter feedbackConverter) {
        this.feedbackRepository = feedbackRepository;
        this.usersRepository = usersRepository;
        this.feedbackConverter = feedbackConverter;
    }

    @Override
    public ResponseEntity<?> save(User user, FeedbackDTO feedbackDTO) {
        Users users = usersRepository.findByUsername(user.getUsername());
        feedbackDTO.setUserId(users.getId());
        return new ResponseEntity<>(feedbackRepository.save(feedbackConverter.fromDTOToFeedback(feedbackDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> get(User user, Long feedbackId) {
        if(feedbackRepository.findById(feedbackId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        Feedback feedback = feedbackRepository.getById(feedbackId);
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(feedbackConverter.fromFeedbackToDTO(feedback), HttpStatus.OK);
        }
        if(users.getId().equals(feedback.getUserId()) || feedback.isVisibility().equals(0)){
            return new ResponseEntity<>(feedbackConverter.fromFeedbackToDTO(feedback), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> changeInfo(User user, FeedbackDTO feedbackDTO) {
        if(feedbackRepository.findById(feedbackDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        Feedback feedback = feedbackRepository.getById(feedbackDTO.getId());
        feedback.setText(feedbackDTO.getText());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setVisibility(1);
        if(users.getId().equals(feedback.getUserId())){
            return new ResponseEntity<>(feedbackRepository.save(feedback), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> changeVisibility(Long feedbackId, Integer visibility) {
        if(feedbackRepository.findById(feedbackId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        feedbackRepository.changeVisibility(feedbackId, visibility);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllHidden() {
        return new ResponseEntity<>(feedbackRepository.getAllHidden().stream().map(feedbackConverter::fromFeedbackToDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllForProduct(User user, Long productId) {
        List<Feedback> feedbacks = feedbackRepository.getAllForProduct(productId);
        if(feedbacks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            feedbacks.forEach(feedback -> {if(feedback.isVisibility().equals(1)) feedbacks.remove(feedback);});
            return new ResponseEntity<>(feedbacks.stream().map(feedbackConverter::fromFeedbackToDTO), HttpStatus.OK);
        }else{
            feedbacks.forEach(feedback -> {if(feedback.isVisibility().equals(0)) feedbacks.remove(feedback);});
            return new ResponseEntity<>(
                    feedbacks.stream().map(feedbackConverter::fromFeedbackToDTO),
                    HttpStatus.OK
            );
        }
    }

    @Override
    public ResponseEntity<?> getAllForUser(User user, Long userId) {
        List<Feedback> feedbacks = feedbackRepository.getAllForUser(userId);
        if(feedbacks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            feedbacks.forEach(feedback -> {if(feedback.isVisibility().equals(1)) feedbacks.remove(feedback);});
            return new ResponseEntity<>(feedbacks.stream().map(feedbackConverter::fromFeedbackToDTO), HttpStatus.OK);
        }else{
            feedbacks.forEach(feedback -> {if(feedback.isVisibility().equals(0)) feedbacks.remove(feedback);});
            return new ResponseEntity<>(
                    feedbacks.stream().map(feedbackConverter::fromFeedbackToDTO),
                    HttpStatus.OK
            );
        }
    }

    @Override
    public ResponseEntity<?> delete(User user, Long feedbackId) {
        if(feedbackRepository.findById(feedbackId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        Feedback feedback = feedbackRepository.getById(feedbackId);
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin")) ||
           users.getId().equals(feedback.getUserId())) {
            feedbackRepository.deleteById(feedbackId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
