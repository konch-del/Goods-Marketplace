package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderData {

    public static ArrayList<Order> getOrders(){
        ArrayList<Order> list = new ArrayList<>();

        Order order = new Order();
        order.setId(1L);
        order.setMark(1);
        order.setDeliveryDate(new GregorianCalendar());
        order.setAddress("abc");
        order.setComm("qwerty");
        order.setStatus("123");
        order.setDateCreated(new GregorianCalendar());
        order.setModifiedDate(new GregorianCalendar());
        order.setModDateOS(new GregorianCalendar());
        order.setUser(2L);
        order.setSu(3L);
        //Bad
        Order order2 = new Order();
        order2.setId(3L);
        order2.setMark(1);
        order2.setDeliveryDate(new GregorianCalendar());
        //order2.setAddress("abc");
        order2.setComm("qwerty");
        order2.setStatus("123");
        order2.setDateCreated(new GregorianCalendar());
        order2.setModifiedDate(new GregorianCalendar());
        order2.setModDateOS(new GregorianCalendar());
        order2.setUser(2L);
        order2.setSu(3L);

        Order order3 = new Order();
        order3.setId(1L);
        order3.setMark(1);
        order3.setDeliveryDate(new GregorianCalendar());
        order3.setAddress("abc");
        order3.setComm("qwerty12345");
        order3.setStatus("123");
        order3.setDateCreated(new GregorianCalendar());
        order3.setModifiedDate(new GregorianCalendar());
        order3.setModDateOS(new GregorianCalendar());
        order3.setUser(2L);
        order3.setSu(3L);

        list.add(order);
        list.add(order2);
        list.add(order3);

        return list;
    }
}
