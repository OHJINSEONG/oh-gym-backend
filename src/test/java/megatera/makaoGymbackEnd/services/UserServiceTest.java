package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    private UserRepository userRepository;

    private UserService userService;

    private KakaoService kakaoService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        kakaoService = mock(KakaoService.class);
        userService = new UserService(userRepository, kakaoService, productService, orderService, ptTicketService);
    }

    @Test
    void findByEmail() {
        String email = "monika1117@naver.com";

        given(userRepository.findByEmail(email)).willReturn(
                Optional.of(User.fake(new UserName("오진성")))
        );

        Optional<UserDto> userDto = userService.findByEmail(email);

        assertThat(userDto.get().getUserName()).isEqualTo("오진성");
    }

    @Test
    void find() {
        given(userRepository.getReferenceById(any())).willReturn(
                User.fake(new UserName("오진성"))
        );

        UserDto userDto = userService.find(1L);

        assertThat(userDto.getUserName()).isEqualTo("오진성");
    }

    @Test
    void list() {
        given(userRepository.findAll()).willReturn(List.of(
                User.fake(new UserName("오진성")),
                User.fake(new UserName("오진욱")),
                User.fake(new UserName("배준형"))
        ));

        List<UserDto> userDtos = userService.list();

        assertThat(userDtos).hasSize(3);
    }

    @Test
    void create() {
        HashMap<String, String> userInformation = new HashMap<>();

        userInformation.put("nickname", "오진성");
        userInformation.put("email", "monika1117@naver.com");

        given(kakaoService.getUser(any())).willReturn(
                userInformation
        );

        UserDto userDto = userService.create("kakaoAccessToken");

        assertThat(userDto.getUserName()).isEqualTo("오진성");
    }
}