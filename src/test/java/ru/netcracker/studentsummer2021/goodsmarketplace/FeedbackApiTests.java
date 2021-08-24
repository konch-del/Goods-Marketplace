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
import ru.netcracker.studentsummer2021.goodsmarketplace.data.FeedbackData;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Feedback;
import ru.netcracker.studentsummer2021.goodsmarketplace.repo.FeedbackRepository;
import ru.netcracker.studentsummer2021.goodsmarketplace.service.feedback.FeedbackConverter;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class FeedbackApiTests {

    private FeedbackConverter feedbackConverter = new FeedbackConverter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private FeedbackRepository feedbackRepository;

    @WithMockUser(authorities = "user")
    @Test
    public void testCreate() throws Exception{
        ArrayList<Feedback> list = FeedbackData.getFeedbacks();

        when((feedbackRepository.save(any()))).thenReturn(list.get(0));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/feedback/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(0))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(0)))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testCreateBad() throws Exception{
        ArrayList<Feedback> list = FeedbackData.getFeedbacks();

        when((feedbackRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/feedback/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testChangeInfo() throws Exception{
        ArrayList<Feedback> list = FeedbackData.getFeedbacks();

        when((feedbackRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(list.get(0)));
        when((feedbackRepository.save(any()))).thenReturn(list.get(2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/feedback/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(2))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(2)))));
    }

    @WithMockUser(authorities = "user")
    @Test
    public void testChangeInfoBad() throws Exception{
        ArrayList<Feedback> list = FeedbackData.getFeedbacks();

        //when((feedbackRepository.findById(any()))).thenReturn(null);
        when((feedbackRepository.save(any()))).thenReturn(list.get(1));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/feedback/changeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(feedbackConverter.fromFeedbackToDTO(list.get(1))));

        mockMvc.perform(mockRequest
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
