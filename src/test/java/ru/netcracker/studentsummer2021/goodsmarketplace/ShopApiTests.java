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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ShopData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Shop;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ShopRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.UsersRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.shops.ShopsConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.netcracker.studentsummer2021.goodsmarketplace.data.UsersData.data;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class ShopApiTests {

    private ShopsConverter shopsConverter = new ShopsConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ShopRepository shopRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateShop() throws Exception{
        Shop shop = ShopData.getShop();

        when((shopRepository.save(any()))).thenReturn(shop);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/shops/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(shopsConverter.fromShopToShopPublic(shop)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(shopsConverter.fromShopToShopAdmin(shop))));
    }
}
