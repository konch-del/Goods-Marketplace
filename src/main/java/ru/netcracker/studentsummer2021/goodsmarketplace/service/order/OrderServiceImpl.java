package ru.netcracker.studentsummer2021.goodsmarketplace.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.order.OrderPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.OrderRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.SalesUnitRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final UsersRepository usersRepository;
    private final SalesUnitRepository salesUnitRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, UsersRepository usersRepository, SalesUnitRepository salesUnitRepository) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.usersRepository = usersRepository;
        this.salesUnitRepository = salesUnitRepository;
    }

    @Override
    public ResponseEntity<?> save(User user, OrderPublicDTO orderPublicDTO) {
        Users users = usersRepository.findByUsername(user.getUsername());
        Order order = orderConverter.fromPublicDTOToOrder(users, orderPublicDTO);
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            return new ResponseEntity<>(orderConverter.fromOrderToAdminDTO(orderRepository.save(order)), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(orderConverter.fromOrderToPublicDTO(orderRepository.save(order)), HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> get(User user, Long orderId) {
        if(orderRepository.findById(orderId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderRepository.getById(orderId);
        Users users = usersRepository.findByUsername(user.getUsername());
        SalesUnit salesUnit = salesUnitRepository.getById(order.getSu());
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(orderConverter.fromOrderToAdminDTO(order), HttpStatus.OK);
        }
        if(order.getUser().equals(users.getId()) ||
                users.getShopId().equals(salesUnit.getShop())){
            return new ResponseEntity<>(orderConverter.fromOrderToPublicDTO(order), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAll(User user, Long shopId) {
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            List<Order> orders = orderRepository.findByShopId(shopId);
            if(orders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(orders.stream().map(orderConverter::fromOrderToAdminDTO), HttpStatus.OK);
            }
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        List<Order> orders = orderRepository.findByShopId(users.getShopId());
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(orders.stream().map(orderConverter::fromOrderToPublicDTO), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getForUser(User user, Long userId) {
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            List<Order> orders = orderRepository.findByUserId(userId);
            if(orders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(orders.stream().map(orderConverter::fromOrderToAdminDTO), HttpStatus.OK);
            }
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        List<Order> orders = orderRepository.findByUserId(users.getId());
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(orders.stream().map(orderConverter::fromOrderToPublicDTO), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> changeInfo(User user, OrderPublicDTO orderPublicDTO) {
        if(orderRepository.findById(orderPublicDTO.getId()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderRepository.getById(orderPublicDTO.getId());
        order.setComm(orderPublicDTO.getComm());
        order.setMark(orderPublicDTO.getMark());
        order.setAddress(orderPublicDTO.getAddress());
        order.setDeliveryDate(orderPublicDTO.getDeliveryDate());
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(orderConverter.fromOrderToAdminDTO(orderRepository.save(order)), HttpStatus.OK);
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        List<Long> orders = orderRepository.findByShopId(users.getShopId()).stream().map(Order::getId).collect(Collectors.toList());
        if(orders.contains(order.getId())){
            return new ResponseEntity<>(orderConverter.fromOrderToPublicDTO(orderRepository.save(order)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> changeStatus(User user, Long orderId, String status) {
        if(orderRepository.findById(orderId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderRepository.getById(orderId);
        order.setStatus(status);
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            return new ResponseEntity<>(orderConverter.fromOrderToAdminDTO(orderRepository.save(order)), HttpStatus.OK);
        }
        Users users = usersRepository.findByUsername(user.getUsername());
        List<Long> orders = orderRepository.findByShopId(users.getShopId()).stream().map(Order::getId).collect(Collectors.toList());
        if(orders.contains(order.getId())){
            return new ResponseEntity<>(orderConverter.fromOrderToPublicDTO(orderRepository.save(order)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> delete(Long orderId) {
        if(orderRepository.findById(orderId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderRepository.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
