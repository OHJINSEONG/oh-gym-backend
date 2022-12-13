package megatera.makaoGymbackEnd.controllers;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserLecturesController {
    private final LectureService lectureService;

    public UserLecturesController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("{id}/lectures")
    public List<LectureDto> findLectureList(
            @PathVariable Long id
    ) {
        return lectureService.userLectures(id);
    }
}

