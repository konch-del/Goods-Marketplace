package ru.netcracker.studentsummer2021.goodsmarketplace.data;

import ru.netcracker.studentsummer2021.goodsmarketplace.dto.users.UsersPrivatDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static ru.netcracker.studentsummer2021.goodsmarketplace.models.Role.*;

public class UsersData {
    public static ArrayList<Users> data(){
        Users user1 = new Users();
        user1.setId(1L);
        user1.setAccountType(ADMIN);
        user1.setUsername("admin");
        user1.setPassword("$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG");
        user1.setFcs("III");
        user1.setCity("0");
        user1.setAddress("ssg");
        user1.setEmail("test@test.com");
        user1.setPhoneNumber("8005553535");
        user1.setDateOfCreation(new GregorianCalendar());
        user1.setLastLoginDate(new GregorianCalendar());

        Users user2 = new Users();
        user2.setId(2L);
        user2.setAccountType(USER);
        user2.setUsername("user1");
        user2.setPassword("$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG");
        user2.setFcs("III");
        user2.setCity("0");
        user2.setAddress("ssg");
        user2.setEmail("user@test.com");
        user2.setPhoneNumber("8005563535");
        user2.setDateOfCreation(new GregorianCalendar());
        user2.setLastLoginDate(new GregorianCalendar());

        //Bad User
        Users user3 = new Users();
        user3.setId(2L);
        user3.setAccountType(USER);
        user3.setUsername("");
        user3.setPassword("$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG");
        user3.setFcs("III");
        user3.setCity("0");
        user3.setAddress("ssg");
        user3.setEmail("user@test.com");
        user3.setPhoneNumber("8005563535");
        user3.setDateOfCreation(new GregorianCalendar());
        user3.setLastLoginDate(new GregorianCalendar());
        //ChangeInfo
        Users user4 = new Users();
        user4.setId(2L);
        user4.setAccountType(USER);
        user4.setUsername("user1");
        user4.setPassword("$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG");
        user4.setFcs("qwertyuo[");
        user4.setCity("0");
        user4.setAddress("ssg");
        user4.setEmail("user@test.com");
        user4.setPhoneNumber("8005563535");
        user4.setDateOfCreation(new GregorianCalendar());
        user4.setLastLoginDate(new GregorianCalendar());
        //Change Email
        Users user5 = new Users();
        user5.setId(2L);
        user5.setAccountType(USER);
        user5.setUsername("user1");
        user5.setPassword("$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG");
        user5.setFcs("III");
        user5.setCity("0");
        user5.setAddress("ssg");
        user5.setEmail("qwerty@qwerty.com");
        user5.setPhoneNumber("8005563535");
        user5.setDateOfCreation(new GregorianCalendar());
        user5.setLastLoginDate(new GregorianCalendar());

        ArrayList<Users> users  = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }

    public static UsersPrivatDTO changeinfo(){
        UsersPrivatDTO usersPrivatDTO = new UsersPrivatDTO();
        usersPrivatDTO.setFcs("qwertyuo[");
        usersPrivatDTO.setId(2L);
        return usersPrivatDTO;
    }
}
