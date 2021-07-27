package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.feedback.FeedbackDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.feedback.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PutMapping("/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal User user, @RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.save(user, feedbackDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@AuthenticationPrincipal User user, @RequestParam Long id){
        return feedbackService.get(user, id);
    }

    @PostMapping("/changeInfo")
    public ResponseEntity<?> changeInfo(@AuthenticationPrincipal User user, @RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.changeInfo(user, feedbackDTO);
    }

    @PostMapping("/changeVisibility")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> changeVisibility(@RequestParam Long id, @RequestParam Integer visibility){
        return feedbackService.changeVisibility(id, visibility);
    }

    @GetMapping("/getAllHidden")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> getAllHidden(){
        return feedbackService.getAllHidden();
    }

    @GetMapping("/getAllForProduct")
    public ResponseEntity<?> getAllForProduct(@AuthenticationPrincipal User user, @RequestParam Long id){
        return feedbackService.getAllForProduct(user, id);
    }

    @GetMapping("/getAllForUser")
    public ResponseEntity<?> getAllForUser(@AuthenticationPrincipal User user, @RequestParam Long id){
        return feedbackService.getAllForUser(user, id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @RequestParam Long id){
        return feedbackService.delete(user, id);
    }

}
