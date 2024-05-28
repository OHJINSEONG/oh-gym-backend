package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.GoogleAccessTokenDto;
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

    @PostMapping("kakao")
    public String kakakoUserRegister(
            @RequestBody KakaoAccessTokenDto kakaoAccessTokenDto
    ) {
        UserDto userDto = userService.kakaoUserRegister(kakaoAccessTokenDto.getKakaoAccessToken());

        String context = userDto.getUserName() + "님 회원가입을 축하드립니다!";

        notificationService.sendNotification(userDto.getId(), context, "SignUp");

        return jwtUtil.encode(userDto.getId());
    }

    @PostMapping("google")
    public String googleUserRegister(
            @RequestBody GoogleAccessTokenDto googleAccessTokenDto
    ) {
        UserDto userDto = userService.googleUserRegister(googleAccessTokenDto.getGoogleAccessToken());

        String context = userDto.getUserName() + "님 회원가입을 축하드립니다!";

        notificationService.sendNotification(userDto.getId(), context, "SignUp");

        return jwtUtil.encode(userDto.getId());
    }
}
