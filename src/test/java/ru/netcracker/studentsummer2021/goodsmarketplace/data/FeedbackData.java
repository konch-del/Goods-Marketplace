package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FeedbackData {

    public static ArrayList<Feedback> getFeedbacks(){
        ArrayList<Feedback> list = new ArrayList<>();

        Feedback feedback = new Feedback();
        feedback.setId(1L);
        feedback.setVisibility(0);
        feedback.setText("abc");
        feedback.setRating(5);
        feedback.setDateCreated(new GregorianCalendar());
        feedback.setUserId(2L);
        feedback.setProductId(3L);
        feedback.setOrderId(4L);

        Feedback feedback2 = new Feedback();
        feedback2.setId(2L);
        feedback2.setVisibility(0);
        feedback2.setText("abc");
        feedback2.setRating(5);
        feedback2.setDateCreated(new GregorianCalendar());
        feedback2.setUserId(2L);
        //feedback2.setProductId(3L);
        feedback2.setOrderId(4L);

        Feedback feedback3 = new Feedback();
        feedback3.setId(1L);
        feedback3.setVisibility(0);
        feedback3.setText("abc");
        feedback3.setRating(5);
        feedback3.setDateCreated(new GregorianCalendar());
        feedback3.setUserId(2L);
        feedback3.setProductId(3L);
        feedback3.setOrderId(4L);

        list.add(feedback);
        list.add(feedback2);
        list.add(feedback3);

        return list;
    }
}
