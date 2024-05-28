package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.exceptions.InValidEmail;
import megatera.makaoGymbackEnd.exceptions.RequestFailed;
import megatera.makaoGymbackEnd.services.KakaoService;
import megatera.makaoGymbackEnd.services.UserService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        HashMap<String, String> kakaoAccessToken = kakaoService.getAccessToken(code);

        HashMap<String, String> userInformation = kakaoService.getUser(kakaoAccessToken.get("accessToken"));

        Optional<UserDto> userDto = userService.findByEmail(userInformation.get("email"));

        String accessToken = userDto.map(dto -> jwtUtil.encode(dto.getId())).orElse(null);

        return new LoginResultDto(kakaoAccessToken.get("accessToken"), accessToken);
    }

    @PostMapping("session/logout")
    public void logout(
            @RequestBody KakaoAccessTokenDto kakaoAccessTokenDto
    ) {
        System.out.println(kakaoAccessTokenDto.getKakaoAccessToken());

        try {
            String kakaoAccessToken = kakaoAccessTokenDto.getKakaoAccessToken();
            if (kakaoAccessToken == null || kakaoAccessToken.isEmpty()) {
                throw new IllegalArgumentException("Invalid Kakao access token");
            }

            kakaoService.logout(kakaoAccessToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to logout from Kakao", e);
        }
    }

    @ExceptionHandler(InValidEmail.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto invalidEmail(InValidEmail inValidEmail) {
        return new InValidEmailErrorDto(inValidEmail.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto exception() {
        return new InValidEmailErrorDto("카카오 이메일 잘못");
    }
}
