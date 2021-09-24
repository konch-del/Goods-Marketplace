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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.CategoryData;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryWithHierarchy;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CategoryRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.category.CategoryConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class CategoryApiTests {

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(0)))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/category/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoBadReq() throws Exception{
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.save(any()))).thenReturn(list.get(1));
        when((categoryRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when(categoryRepository.getById(any())).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/category/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoNotFound() throws Exception{
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.save(any()))).thenReturn(list.get(1));
        when((categoryRepository.findById(1L))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when(categoryRepository.getById(any())).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/category/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(2))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.save(any()))).thenReturn(list.get(3));
        when((categoryRepository.findById(1L))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when(categoryRepository.getById(any())).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/category/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(categoryConverter.fromCategoryToDTO(list.get(3))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(authorities = "user")
    @Test
    public void getWithHierarchy() throws Exception{
        CategoryWithHierarchy category = CategoryData.getCategoryWithHierarchy();
        ArrayList<Category> list = CategoryData.getCategory();

        when((categoryRepository.findById(1L))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((categoryRepository.findById(4L))).thenReturn(java.util.Optional.ofNullable(list.get(3)));
        when((categoryRepository.getById(any()))).thenReturn(list.get(0)).thenReturn(list.get(3));
        //when((categoryRepository.getById(4L))).thenReturn(list.get(3));
        //when(categoryRepository.getById(any())).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/category/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "1");

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(category)));
    }
}
