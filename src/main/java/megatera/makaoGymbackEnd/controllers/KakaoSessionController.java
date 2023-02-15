package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.KakaoAccessTokenDto;
import megatera.makaoGymbackEnd.dtos.LoginResultDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.services.KakaoService;
import megatera.makaoGymbackEnd.services.UserService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/kakao")
public class KakaoSessionController {
    private final KakaoService kakaoService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public KakaoSessionController(KakaoService kakaoService, UserService userService, JwtUtil jwtUtil) {
        this.kakaoService = kakaoService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("session")
    public LoginResultDto login(
            @RequestParam("code") String code
    ) {
        String kakaoAccessToken = kakaoService.getAccessToken(code);

        HashMap<String, String> userInformation = kakaoService.getUser(kakaoAccessToken);

        Optional<UserDto> userDto = userService.findByEmail(userInformation.get("email"));

        String accessToken = userDto.map(dto -> jwtUtil.encode(dto.getId())).orElse(null);

        return new LoginResultDto(kakaoAccessToken, accessToken);
    }

    @PostMapping("session")
    public void logout(
            @RequestBody KakaoAccessTokenDto kakaoAccessTokenDto
    ) {
        kakaoService.logout(kakaoAccessTokenDto.getKakaoAccessToken());
    }
}
