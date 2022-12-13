package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.OrderService;
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

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private LectureService lectureService;

    @BeforeEach
    void setup() {
        orderService = mock(OrderService.class);
        lectureService = mock(LectureService.class);
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"productId\": \"1\" , " +
                                "\"prStartDate\" :\"2022/12/06\"" +
                                "}"))
                .andExpect(status().isCreated());
    }
}
