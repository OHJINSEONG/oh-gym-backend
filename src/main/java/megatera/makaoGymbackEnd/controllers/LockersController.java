package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.LockerDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerDto;
import megatera.makaoGymbackEnd.dtos.LockerPatchDto;
import megatera.makaoGymbackEnd.services.LockersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lockers")
public class LockersController {
    private final LockersService lockersService;

    public LockersController(LockersService lockersService) {
        this.lockersService = lockersService;
    }

    @GetMapping
    private List<LockerDto> list() {
        return lockersService.list();
    }

    @GetMapping("{id}")
    private LockerDetailDto list(
            @PathVariable Long id
    ) {
        return lockersService.find(id);
    }

    @PatchMapping("{id}")
    private LockerDto patch(
            @PathVariable Long id,
            @RequestBody LockerPatchDto lockerPatchDto,
            @RequestAttribute("userId") Long userId
    ) {
        return lockersService.patch(id, userId,lockerPatchDto.getType());
    }
}
