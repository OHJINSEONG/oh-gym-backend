package megatera.makaoGymbackEnd.admin.controllers;

import megatera.makaoGymbackEnd.dtos.LockerDetailDto;
import megatera.makaoGymbackEnd.dtos.LockerDto;
import megatera.makaoGymbackEnd.dtos.LockerRequestDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.services.LockersService;
import megatera.makaoGymbackEnd.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-lockers")
public class AdminLockerController {
    private final LockersService lockersService;

    public AdminLockerController(LockersService lockersService) {
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
            @RequestBody LockerRequestDto lockerRequestDto
    ) {
        return lockersService.adminPatch(id, lockerRequestDto.getRequestMessage());
    }
}
