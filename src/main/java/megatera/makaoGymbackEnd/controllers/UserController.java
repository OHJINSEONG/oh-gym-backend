package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.dtos.KakaoAccessTokenDto;
import megatera.makaoGymbackEnd.services.UserService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public UserDto find(
            @RequestAttribute("userId") Long userId
    ) {
        return userService.find(userId);
    }


    @PostMapping
    public String register(
            @RequestBody KakaoAccessTokenDto kakaoAccessTokenDto
    ) {
        UserDto userDto = userService.create(kakaoAccessTokenDto.getKakaoAccessToken());

        return jwtUtil.encode(userDto.getId());
    }
}
