//package megatera.makaoGymbackEnd.admin.services;
//
//import megatera.makaoGymbackEnd.dtos.AdminOrderResultDto;
//import megatera.makaoGymbackEnd.services.OrderService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin-orders")
//public class AdminOrderService {
//    private final OrderService orderService;
//
//    public AdminOrderService(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @GetMapping
//    public List<AdminOrderResultDto> list() {
//        return orderService.adminList();
//    }
//}
