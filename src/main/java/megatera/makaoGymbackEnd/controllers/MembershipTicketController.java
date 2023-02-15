package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.LockerTicketDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDetailDto;
import megatera.makaoGymbackEnd.dtos.MembershipTicketDto;
import megatera.makaoGymbackEnd.dtos.PtTicketDto;
import megatera.makaoGymbackEnd.services.MembershipTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership-tickets")
public class MembershipTicketController {
    private final MembershipTicketService membershipTicketService;

    public MembershipTicketController(MembershipTicketService membershipTicketService) {
        this.membershipTicketService = membershipTicketService;
    }

    @GetMapping
    public List<MembershipTicketDetailDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return membershipTicketService.list(userId);
    }

    @GetMapping("use")
    public MembershipTicketDetailDto findInUse(
            @RequestAttribute("userId") Long userId
    ) {
        return membershipTicketService.findInUse(userId);
    }

    @PatchMapping("{id}")
    private MembershipTicketDetailDto use(
            @PathVariable Long id,
            @RequestParam("date") String date
    ) {
        return membershipTicketService.use(id, date);
    }
}
