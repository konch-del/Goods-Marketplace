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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ManufacturerData;
import ru.netcracker.studentsummer2021.goodsmarketplace.data.PictureProductData;
import ru.netcracker.studentsummer2021.goodsmarketplace.data.ProductData;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductSaveDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Category;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Manufacturer;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Product;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.CategoryRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ManufacturerRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.PictureProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.ProductRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.category.CategoryConverter;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.manufacturer.ManufacturerConverter;
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
public class ProductApiTests {

    private ProductConverter productConverter = new ProductConverter();
    private ManufacturerConverter manufacturerConverter = new ManufacturerConverter();

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ManufacturerRepository manufacturerRepository;

    @MockBean
    private PictureProductRepository pictureProductRepository;

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreate() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<ProductSaveDTO> saveDTO = ProductData.getSaveDTO();

        when((productRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(productConverter.fromPublicDTOToProduct(saveDTO.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.get(0))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<ProductSaveDTO> saveDTO = ProductData.getSaveDTO();

        when((productRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(productConverter.fromPublicDTOToProduct(saveDTO.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<ProductSaveDTO> saveDTO = ProductData.getSaveDTO();

        when((productRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((productRepository.getById(any()))).thenReturn(list.get(0));
        when((productRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(productConverter.fromPublicDTOToProduct(saveDTO.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(list.get(0))));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoBad() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<ProductSaveDTO> saveDTO = ProductData.getSaveDTO();

        when((productRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((productRepository.getById(any()))).thenReturn(list.get(0));
        when((productRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(productConverter.fromPublicDTOToProduct(saveDTO.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testChangeInfoNotFound() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<ProductSaveDTO> saveDTO = ProductData.getSaveDTO();

        //when((productRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((productRepository.getById(any()))).thenReturn(list.get(0));
        when((productRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(productConverter.fromPublicDTOToProduct(saveDTO.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void testGetProduct() throws Exception{
        ArrayList<Product> list = ProductData.getProducts();
        ArrayList<Category> categories = CategoryData.getCategory();
        ArrayList<Manufacturer> manufacturers = ManufacturerData.getManufacturers();
        ArrayList<PictureProduct> pictureProducts = PictureProductData.getPicturesP();

        when((productRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((productRepository.getById(any()))).thenReturn(list.get(0));
        when((categoryRepository.getById(any()))).thenReturn(categories.get(0));
        when((manufacturerRepository.getById(any()))).thenReturn(manufacturers.get(0));
        when((pictureProductRepository.findByProductId(any()))).thenReturn(pictureProducts);

        CategoryDTO categoryDTO = categoryConverter.fromCategoryToDTO(categories.get(0));
        ManufacturerDTO manufacturerDTO = manufacturerConverter.fromManufacturerToDTO(manufacturers.get(0));
        ProductPublicDTO productPublicDTO = productConverter.fromProductToPublicDTO(list.get(0), categoryDTO, manufacturerDTO, pictureProducts.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/product/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "1");

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(productPublicDTO)));
    }
}
