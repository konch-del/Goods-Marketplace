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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.CharacteristicData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CharacteristicRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.characteristic.CharacteristicConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class CharacteristicApiTests {

    private CharacteristicConverter characteristicConverter = new CharacteristicConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private CharacteristicRepository characteristicRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<Characteristic> list = CharacteristicData.getCharacteristic();

        when((characteristicRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/characteristic/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(characteristicConverter.fromCharacteristicToDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(characteristicConverter.fromCharacteristicToDTO(list.get(0)))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<Characteristic> list = CharacteristicData.getCharacteristic();

        when((characteristicRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/characteristic/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(characteristicConverter.fromCharacteristicToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "user")
    @Test
    public void getAllForCategory() throws Exception{
        ArrayList<Characteristic> list = CharacteristicData.getCharacteristic();

        when((characteristicRepository.getByCategoryId(any()))).thenReturn(list.stream().collect(Collectors.toList()));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/characteristic/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "12");

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.stream().map(characteristicConverter::fromCharacteristicToDTO).collect(Collectors.toList()))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void getAllForCategoryNotFound() throws Exception{
        ArrayList<Characteristic> list = CharacteristicData.getCharacteristic();

        when((characteristicRepository.getByCategoryId(12L))).thenReturn(list.stream().collect(Collectors.toList()));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/characteristic/getAll")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "15");

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}
