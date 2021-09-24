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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.PictureProductData;
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ProductData;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.PictureProductDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.PictureProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.product.ProductConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class PictureProductApiTests {

    private ProductConverter productConverter = new ProductConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PictureProductRepository pictureProductRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<PictureProductDTO> list = PictureProductData.getPictures();

        when((pictureProductRepository.save(any()))).thenReturn(productConverter.fromPictureDTOToPictureProduct(list.get(0)));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/product/createPicture")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(list.get(0)));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.get(0))));
    }
}
