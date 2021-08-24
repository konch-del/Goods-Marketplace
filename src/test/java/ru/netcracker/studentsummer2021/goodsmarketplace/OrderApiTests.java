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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.netcracker.studentsummer2021.goodsmarketplace.data.OrderData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Order;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.OrderRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.order.OrderConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class OrderApiTests {

    private OrderConverter orderConverter = new OrderConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private OrderRepository orderRepository;

    @WithMockUser(authorities = "user")
    @Test
    public void testCreateUser() throws Exception{
        ArrayList<Order> list = OrderData.getOrders();

        when((orderRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(0)))));

    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateAdmin() throws Exception{
        ArrayList<Order> list = OrderData.getOrders();

        when((orderRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(orderConverter.fromOrderToAdminDTO(list.get(0)))));

    }

    @WithMockUser(authorities = "user")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<Order> list = OrderData.getOrders();

        when((orderRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<Order> list = OrderData.getOrders();

        when((orderRepository.findById(1L))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((orderRepository.getById(any()))).thenReturn(list.get(0));
        when((orderRepository.save(any()))).thenReturn(list.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(2))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(orderConverter.fromOrderToAdminDTO(list.get(2)))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoBad() throws Exception{
        ArrayList<Order> list = OrderData.getOrders();

        when((orderRepository.findById(1L))).thenReturn(null);
        when((orderRepository.save(any()))).thenReturn(list.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(orderConverter.fromOrderToPublicDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
