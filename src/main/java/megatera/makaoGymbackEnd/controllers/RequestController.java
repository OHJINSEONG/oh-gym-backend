package megatera.makaoGymbackEnd.controllers;

import java.util.List;

import megatera.makaoGymbackEnd.dtos.LectureDto;
import megatera.makaoGymbackEnd.dtos.RequestRegisterDto;
import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.RequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;
    private final LectureService lectureService;

    public RequestController(RequestService requestService, LectureService lectureService) {
        this.requestService = requestService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public RequestResultDto create(
            @RequestBody RequestRegisterDto requestRegisterDto,
            @RequestAttribute("userId") Long userId
    ) {
        LectureDto lectureDto = lectureService.reserve(
                userId,
                requestRegisterDto.getReceiverId(),
                requestRegisterDto.getContext(),
                requestRegisterDto.getSenderName()
        );

        return requestService.create(
                userId,
                requestRegisterDto.getReceiverId(),
                lectureDto.getId(),
                requestRegisterDto.getContext(),
                requestRegisterDto.getSenderName()
        );
    }

    @GetMapping("{id}")
    public List<RequestResultDto> receivedRequestList(
            @PathVariable Long id
    ) {
        return requestService.findByReceiverId(id);
    }

    @PatchMapping
    public void requestsChecked(
            @RequestParam("trainerId") Long trainerId
    ) {
        requestService.makeChecked(trainerId);
    }

    @DeleteMapping("{id}")
    public void requestDelete(
            @PathVariable Long id
    ) {
        requestService.delete(id);
    }
}
