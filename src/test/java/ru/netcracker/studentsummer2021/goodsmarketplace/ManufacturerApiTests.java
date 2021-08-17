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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ManufacturerData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ManufacturerRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer.ManufacturerConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class ManufacturerApiTests {

    private ManufacturerConverter manufacturerConverter = new ManufacturerConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ManufacturerRepository manufacturerRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateManufacturer() throws Exception{
        ArrayList<Manufacturer> manufacturers = ManufacturerData.getManufacturers();

        when((manufacturerRepository.save(any()))).thenReturn(manufacturers.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/manufacturer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(manufacturerConverter.fromManufacturerToDTO(manufacturers.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(manufacturerConverter.fromManufacturerToDTO(manufacturers.get(0)))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateManufacturerBad() throws Exception{
        ArrayList<Manufacturer> manufacturers = ManufacturerData.getManufacturers();

        when((manufacturerRepository.save(any()))).thenReturn(manufacturers.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/manufacturer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(manufacturerConverter.fromManufacturerToDTO(manufacturers.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
