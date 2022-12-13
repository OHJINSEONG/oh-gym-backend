package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.UserDto;
import megatera.makaoGymbackEnd.models.User;
import megatera.makaoGymbackEnd.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto find() {
        User user = userRepository.getReferenceById(1L);

        return user.toDto();
    }
}
