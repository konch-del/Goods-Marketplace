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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ValuesCharactData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Characteristic;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.ValuesCharacter;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CharacteristicRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ValuesCharacterRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.valuesCharacter.ValuesCharacterConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class ValueCharactApiTests {

    private ValuesCharacterConverter valuesCharacterConverter = new ValuesCharacterConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ValuesCharacterRepository valuesCharacterRepository;

    @MockBean
    private CharacteristicRepository characteristicRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<ValuesCharacter> list = ValuesCharactData.getData();
        ArrayList<Characteristic> characteristics = CharacteristicData.getCharacteristic();

        when((characteristicRepository.getById(any()))).thenReturn(characteristics.get(0));
        when((valuesCharacterRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/valuesCharacter/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(valuesCharacterConverter.fromValuesCharacterToDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(valuesCharacterConverter.fromValuesCharacterToDTO(list.get(0)))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<ValuesCharacter> list = ValuesCharactData.getData();
        ArrayList<Characteristic> characteristics = CharacteristicData.getCharacteristic();

        when((characteristicRepository.getById(any()))).thenReturn(characteristics.get(0));
        when((valuesCharacterRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/valuesCharacter/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(valuesCharacterConverter.fromValuesCharacterToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<ValuesCharacter> list = ValuesCharactData.getData();

        when((valuesCharacterRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((valuesCharacterRepository.getById(any()))).thenReturn(list.get(0));
        when((valuesCharacterRepository.save(any()))).thenReturn(list.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/valuesCharacter/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(valuesCharacterConverter.fromValuesCharacterToDTO(list.get(2))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoBad() throws Exception{
        ArrayList<ValuesCharacter> list = ValuesCharactData.getData();

        when((valuesCharacterRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((valuesCharacterRepository.getById(any()))).thenReturn(list.get(0));
        when((valuesCharacterRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/valuesCharacter/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(valuesCharacterConverter.fromValuesCharacterToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
