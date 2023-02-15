package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.services.PtTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pt-tickets")
public class PtTicketController {
    private final PtTicketService ptTicketService;

    public PtTicketController(PtTicketService ptTicketService) {
        this.ptTicketService = ptTicketService;
    }

    @GetMapping
    public List<PtTicketDetailDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return ptTicketService.list(userId);
    }

    @GetMapping("use")
    public PtTicketDetailDto findInUse(
            @RequestAttribute("userId") Long userId
    ) {
        return ptTicketService.findInUse(userId);
    }

    @PatchMapping("{id}")
    private PtTicketDetailDto use(
            @PathVariable Long id,
            @RequestParam("date") String date
    ) {
        return ptTicketService.use(id, date);
    }
}
