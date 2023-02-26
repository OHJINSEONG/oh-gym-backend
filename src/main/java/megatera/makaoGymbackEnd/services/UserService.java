package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
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
    private final ProductService productService;
    private final OrderService orderService;
    private final PtTicketService ptTicketService;

    public UserService(UserRepository userRepository, KakaoService kakaoService, ProductService productService, OrderService orderService, PtTicketService ptTicketService) {
        this.userRepository = userRepository;
        this.kakaoService = kakaoService;
        this.productService = productService;
        this.orderService = orderService;
        this.ptTicketService = ptTicketService;
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

    public UserDto testUserCreate() {
        List<User> users = userRepository.findAll();

        String name = "테스트용" + users.size();

        String email = "테스트용 이메일" + users.size();

        User user = new User(new UserName(name), email, new Count(0L), new Period(0L));

        user.created();

        userRepository.save(user);

        Optional<ProductDetailDto> ptProductDetailDto = productService.list().stream()
                .filter(productDetailDto -> productDetailDto.getType().equals("PT")).findFirst();

        ptProductDetailDto.ifPresent(productDetailDto ->
                orderService.create(
                        user.toDto().getId(),
                        productDetailDto.getId(),
                        productDetailDto.getOptions().get(0).getId(),
                        "피티",
                        productDetailDto.getOptions().get(0).getPrice(),
                        "주소",
                        "남자",
                        "상세 주소",
                        "생일",
                        "전화 번호",
                        user.userName().value(),
                        "Test"
                ));

        return user.toDto();
    }
}
