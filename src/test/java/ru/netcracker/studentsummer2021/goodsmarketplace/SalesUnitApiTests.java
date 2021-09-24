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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ProductData;
import ru.netcracker.studentsummer2021.goodsmarketplace.data.SalesUnitData;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.SalesUnit;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.SalesUnitRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.product.ProductConverter;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.salesUnit.SalesUnitConverter;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class SalesUnitApiTests {

    private SalesUnitConverter salesUnitConverter = new SalesUnitConverter();
    private ProductConverter productConverter = new ProductConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private SalesUnitRepository salesUnitRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        when((salesUnitRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/salesUnit/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(0)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.get(0))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        when((salesUnitRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/salesUnit/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(1)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        when((salesUnitRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((salesUnitRepository.getById(any()))).thenReturn(list.get(0));
        when((salesUnitRepository.save(any()))).thenReturn(list.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/salesUnit/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(2)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.get(2))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoBad() throws Exception{
        ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        when((salesUnitRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((salesUnitRepository.getById(any()))).thenReturn(list.get(0));
        when((salesUnitRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/salesUnit/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(1)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoNotFound() throws Exception{
        ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        //when((salesUnitRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((salesUnitRepository.getById(any()))).thenReturn(list.get(0));
        when((salesUnitRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/salesUnit/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(1)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testGetAllFoShop() throws Exception{
        /*ArrayList<SalesUnit> list = SalesUnitData.getSalesUnit();

        when((salesUnitRepository.findSalesUnitByShop(any()))).thenReturn(list);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/salesUnit/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "2");

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(3)));*/
    }
}
