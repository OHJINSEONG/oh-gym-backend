package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.services.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainerController.class)
class TrainerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;

    @Test
    void find() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trainers/1"))
                .andExpect(status().isOk());
    }
}