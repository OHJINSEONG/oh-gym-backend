//package megatera.makaoGymbackEnd.admin.services;
//
//import megatera.makaoGymbackEnd.dtos.UserDto;
//import megatera.makaoGymbackEnd.services.UserService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin-users")
//public class AdminUserService {
//    private final UserService userService;
//
//    public AdminUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<UserDto> list() {
//        return userService.list();
//    }
//}
