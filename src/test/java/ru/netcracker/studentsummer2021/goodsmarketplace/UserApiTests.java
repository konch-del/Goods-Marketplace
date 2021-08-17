package ru.netcracker.studentsummer2021.goodsmarketplace;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.users.UsersConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.netcracker.studentsummer2021.goodsmarketplace.data.UsersData.changeinfo;
import static ru.netcracker.studentsummer2021.goodsmarketplace.data.UsersData.data;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class UserApiTests {

    private UsersConverter usersConverter = new UsersConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void testReg() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.save(any()))).thenReturn(users.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(usersConverter.fromUserToUserAdmin(users.get(1))));

        mockMvc.perform(mockRequest
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(usersConverter.fromUserToUserPublic(users.get(1)))));
    }

    @Test
    public void testRegNotValid() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.save(any()))).thenReturn(users.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(usersConverter.fromUserToUserAdmin(users.get(2))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testFindAll() throws Exception {
        ArrayList<Users> users = data();

        when(usersRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/users/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testReadAccFromAdmin() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.findById(2L))).thenReturn(java.util.Optional.ofNullable(users.get(1)));
        when((usersRepository.getById(2L))).thenReturn(users.get(1));

        mockMvc.perform(get("/users/getUserById?id=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(users.get(1))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testReadAccFromSelf() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.findByUsername(anyString()))).thenReturn(users.get(1));
        when((usersRepository.findById(anyLong()))).thenReturn(java.util.Optional.ofNullable(users.get(1)));
        when((usersRepository.getById(anyLong()))).thenReturn(users.get(1));


        mockMvc.perform(get("/users/getUserById?id=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(usersConverter.fromUserToUserDto(users.get(1)))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testReadAccFromOverUser() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.findByUsername(anyString()))).thenReturn(users.get(1));
        when((usersRepository.findById(anyLong()))).thenReturn(java.util.Optional.ofNullable(users.get(0)));
        when((usersRepository.getById(anyLong()))).thenReturn(users.get(0));


        mockMvc.perform(get("/users/getUserById?id=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(usersConverter.fromUserToUserPublic(users.get(0)))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.save(any()))).thenReturn(users.get(3));
        when((usersRepository.findByUsername(anyString()))).thenReturn(users.get(1));
        when((usersRepository.findById(anyLong()))).thenReturn(java.util.Optional.ofNullable(users.get(1)));
        when((usersRepository.getById(anyLong()))).thenReturn(users.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users/changeinfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(changeinfo()));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(usersConverter.fromUserToUserDto(users.get(3)))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testChangeEmail() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.save(any()))).thenReturn(users.get(4));
        when((usersRepository.findByUsername(anyString()))).thenReturn(users.get(1));
        when((usersRepository.findById(anyLong()))).thenReturn(java.util.Optional.ofNullable(users.get(1)));
        when((usersRepository.getById(anyLong()))).thenReturn(users.get(1));

        String pass = "$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc.gOI3z/N4.DKwpsSGfZIrqRkaG";
        String email = "qwerty@qwerty.com";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users/changeEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("pass", pass)
                .param("email", email);

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testChangeEmailWrongPass() throws Exception{
        ArrayList<Users> users = data();

        when((usersRepository.save(any()))).thenReturn(users.get(4));
        when((usersRepository.findByUsername(anyString()))).thenReturn(users.get(1));
        when((usersRepository.findById(anyLong()))).thenReturn(java.util.Optional.ofNullable(users.get(1)));
        when((usersRepository.getById(anyLong()))).thenReturn(users.get(1));

        String pass = "$2y$12$c.wiRLz.mPwae9XakBeICuWHDKc";
        String email = "qwerty@qwerty.com";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users/changeEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("pass", pass)
                .param("email", email);

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}