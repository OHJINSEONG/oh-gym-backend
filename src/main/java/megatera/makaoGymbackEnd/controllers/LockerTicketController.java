package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.LockerTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.services.LockerTicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locker-tickets")
public class LockerTicketController {
    private final LockerTicketService lockerTicketService;

    public LockerTicketController(LockerTicketService lockerTicketService) {
        this.lockerTicketService = lockerTicketService;
    }

    @GetMapping
    private LockerTicketDetailDto findByUserId(
            @RequestAttribute("userId") Long userId
    ) {
        return lockerTicketService.findByUserId(userId);
    }

    @PatchMapping("{id}")
    private LockerTicketDto use(
            @PathVariable Long id,
            @RequestParam("date") String date
    ) {
        return lockerTicketService.use(id, date);
    }

    @PatchMapping("{id}/cancel")
    private LockerTicketDto unUse(
            @PathVariable Long id
    ) {
        return lockerTicketService.unUse(id);
    }
}
