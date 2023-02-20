package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.KakaoAccessTokenDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.services.NotificationService;
import megatera.makaoGymbackEnd.services.UserService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final NotificationService notificationService;

    public UserController(UserService userService, JwtUtil jwtUtil, NotificationService notificationService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.notificationService = notificationService;
    }

    @GetMapping
    public UserDto find(
            @RequestAttribute("userId") Long userId
    ) {
        return userService.find(userId);
    }

    @GetMapping("test")
    public String testLogin() {
        UserDto userDto = userService.testUserCreate();

        String context = userDto.getUserName() + "님 회원가입을 축하드립니다!";

        notificationService.sendNotification(userDto.getId(), context, "SignUp");

        return jwtUtil.encode(userDto.getId());
    }

    @PostMapping
    public String register(
            @RequestBody KakaoAccessTokenDto kakaoAccessTokenDto
    ) {
        UserDto userDto = userService.create(kakaoAccessTokenDto.getKakaoAccessToken());

        String context = userDto.getUserName() + "님 회원가입을 축하드립니다!";

        notificationService.sendNotification(userDto.getId(), context, "SignUp");

        return jwtUtil.encode(userDto.getId());
    }
}
