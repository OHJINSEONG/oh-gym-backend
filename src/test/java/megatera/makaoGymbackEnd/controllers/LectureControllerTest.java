package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.TrainerService;
import megatera.makaoGymbackEnd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LectureController.class)
class LectureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService lectureService;

    @MockBean
    private TrainerService trainerService;

    @MockBean
    private UserService userService;

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures/1"))
                .andExpect(status().isOk());
    }

    @Test
    void register() throws Exception {
        UserDto userDto = User.fake(new UserName("오진성")).toDto();

        given(userService.find()).willReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/lectures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"trainerId\": \"1\" , " +
                                "\"consumerId\" :\"1\" , " +
                                "\"date\": \"2022-12-06T11:00\"" +
                                "}"))
                .andExpect(status().isCreated());
    }
}
