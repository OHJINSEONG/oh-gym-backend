package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.Count;
import megatera.makaoGymbackEnd.models.Period;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.models.UserName;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final KakaoService kakaoService;

    public UserService(UserRepository userRepository, KakaoService kakaoService) {
        this.userRepository = userRepository;
        this.kakaoService = kakaoService;
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(User::toDto);
    }

    public UserDto find(Long id) {
        User user = userRepository.getReferenceById(id);

        return user.toDto();
    }

    public List<UserDto> list() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }

    public UserDto create(String kakaoAccessToken) {
        HashMap<String, String> userInformation = kakaoService.getUser(kakaoAccessToken);

        User user = new User(new UserName(userInformation.get("nickname")), userInformation.get("email"), new Count(0L), new Period(0L));

        user.created();

        userRepository.save(user);

        return user.toDto();
    }
}
