package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.exceptions.RequestFailed;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.TrainerService;
import megatera.makaoGymbackEnd.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;
    private final TrainerService trainerService;
    private final UserService userService;

    public LectureController(LectureService lectureService,
                             TrainerService trainerService,
                             UserService userService) {
        this.lectureService = lectureService;
        this.trainerService = trainerService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public List<LectureDto> list(
            @PathVariable("id") Long trainerId
    ) {
        return lectureService.list(trainerId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LectureDto register(
            @RequestBody LectureRegisterDto lectureRegisterDto
    ) {
        UserDto userDto = userService.find();

        return lectureService.create(
                lectureRegisterDto.getTrainerId(),
                lectureRegisterDto.getConsumerId(),
                lectureRegisterDto.getDate(),
                userDto.getName()
        );
    }

    @ExceptionHandler(RequestFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto requestFailed(RequestFailed requestFailed) {
        return new RequestErrorDto(requestFailed.getCode(), requestFailed.getMessage());
    }
}
