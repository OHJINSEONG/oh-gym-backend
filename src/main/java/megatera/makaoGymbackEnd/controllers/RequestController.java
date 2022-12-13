package megatera.makaoGymbackEnd.controllers;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.RequestChangeStatusDto;
import megatera.makaoGymbackEnd.dtos.RequestRegisterDto;
import megatera.makaoGymbackEnd.dtos.RequestResultDto;
import megatera.makaoGymbackEnd.services.RequestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public RequestResultDto create(
            @RequestBody RequestRegisterDto requestRegisterDto
    ) {
        return requestService.create(
                requestRegisterDto.getSenderId(),
                requestRegisterDto.getReceiverId(),
                requestRegisterDto.getType(),
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
