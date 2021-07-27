package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.order.OrderPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.order.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal User user, @RequestBody OrderPublicDTO orderPublicDTO){
        return orderService.save(user, orderPublicDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return orderService.delete(id);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@AuthenticationPrincipal User user, @RequestParam Long id){
        return orderService.get(user, id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal User user, @RequestParam Long id){
        return orderService.getAll(user, id);
    }

    @GetMapping("/getForUser")
    public ResponseEntity<?> getForUser(@AuthenticationPrincipal User user, @RequestParam Long id){
        return orderService.getForUser(user, id);
    }

    @PostMapping("/changeInfo")
    public ResponseEntity<?> changeInfo(@AuthenticationPrincipal User user, @RequestBody OrderPublicDTO orderPublicDTO){
        return orderService.changeInfo(user, orderPublicDTO);
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@AuthenticationPrincipal User user, @RequestParam Long id, @RequestParam String status){
        return orderService.changeStatus(user, id, status);
    }
}
