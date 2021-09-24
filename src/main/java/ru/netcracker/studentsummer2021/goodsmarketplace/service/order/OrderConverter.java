package ru.netcracker.studentsummer2021.goodsmarketplace.service.order;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.order.OrderAdminDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.order.OrderPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Status;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import java.util.GregorianCalendar;

@Component
public class OrderConverter {

    public Order fromPublicDTOToOrder(Users user , OrderPublicDTO orderPublicDTO){
        Order order = new Order();
        order.setId(orderPublicDTO.getId());
        if(orderPublicDTO.getSuId() != null){
            order.setSu(orderPublicDTO.getSuId());
        }
        if(user.getAccountType().getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
            order.setUser(orderPublicDTO.getUserId());
        }else{
            order.setUser(user.getId());
        }
        order.setAddress(orderPublicDTO.getAddress());
        order.setComm(orderPublicDTO.getComm());
        order.setDateCreated(new GregorianCalendar());
        order.setDeliveryDate(orderPublicDTO.getDeliveryDate());
        order.setMark(orderPublicDTO.getMark());
        order.setStatus(Status.CREATED);
        order.setModDateOS(new GregorianCalendar());
        order.setModifiedDate(new GregorianCalendar());
        return order;
    }

    public OrderPublicDTO fromOrderToPublicDTO(Order order){
        return OrderPublicDTO.builder()
                .id(order.getId())
                .suId(order.getSu())
                .userId(order.getUser())
                .address(order.getAddress())
                .comm(order.getComm())
                .dateCreated(order.getDateCreated())
                .deliveryDate(order.getDeliveryDate())
                .mark(order.getMark())
                .status(order.getStatus())
                .build();
    }

    public OrderAdminDTO fromOrderToAdminDTO(Order order){
        return OrderAdminDTO.builder()
                .id(order.getId())
                .suId(order.getSu())
                .userId(order.getUser())
                .address(order.getAddress())
                .comm(order.getComm())
                .dateCreated(order.getDateCreated())
                .deliveryDate(order.getDeliveryDate())
                .mark(order.getMark())
                .status(order.getStatus())
                .modDateOS(order.getModDateOS())
                .modifiedDate(order.getModifiedDate())
                .build();
    }

}
