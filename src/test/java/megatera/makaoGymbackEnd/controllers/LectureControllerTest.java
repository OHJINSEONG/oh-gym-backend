package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.services.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LectureController.class)
class LectureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService lectureService;

    @BeforeEach
    void setup() {
        lectureService = mock(LectureService.class);
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures"))
                .andExpect(status().isOk());
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/lectures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"orderId\": \"1\" , " +
                                "\"trainer\" :\"오진욱\" , " +
                                "\"consumer\": \"오진성\" , " +
                                "\"ptTimes\" : \"12\" , " +
                                "\"timeOfPt\" : \"11:00\", " +
                                "\"dayOfWeek\" : \"월 수 금\" , " +
                                "\"ptStartDate\" :\"2022/12/06\"" +
                                "}"))
                .andExpect(status().isCreated());
    }
}
