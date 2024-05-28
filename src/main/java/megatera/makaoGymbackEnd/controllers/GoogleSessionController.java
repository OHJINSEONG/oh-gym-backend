package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.GoogleInfoResponse;
import megatera.makaoGymbackEnd.dtos.LoginResultDto;
import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.services.GoogleService;
import megatera.makaoGymbackEnd.services.UserService;
import megatera.makaoGymbackEnd.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GoogleSessionController {
    private final GoogleService googleService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public GoogleSessionController(GoogleService googleService, UserService userService, JwtUtil jwtUtil) {
        this.googleService = googleService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/v1/oauth2/google")
    public String loginUrlGoogle() {
        String googleAuthUrl = googleService.getGoogleOAuth2RedirectUrl();

        return googleAuthUrl;
    }

    @GetMapping("/v1/oauth2/google")
    public LoginResultDto loginGoogle(@RequestParam(value = "code") String code) {
        String googleAccessToken = googleService.getGoogleAccessToken(code);

        ResponseEntity<GoogleInfoResponse> userInformation = googleService.getUser(googleAccessToken);

        Optional<UserDto> userDto = userService.findByEmail(userInformation.getBody().getEmail());

        String accessToken = userDto.map(dto -> jwtUtil.encode(dto.getId())).orElse(null);

        return new LoginResultDto(googleAccessToken, accessToken);
    }
}
