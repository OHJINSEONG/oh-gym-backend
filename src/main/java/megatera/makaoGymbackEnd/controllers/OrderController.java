package megatera.makaoGymbackEnd.controllers;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.OrderDto;
import megatera.makaoGymbackEnd.dtos.OrderResultDto;
import megatera.makaoGymbackEnd.services.LectureService;
import megatera.makaoGymbackEnd.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final LectureService lectureService;
    private final OrderService orderService;

    public OrderController(LectureService lectureService, OrderService orderService) {
        this.lectureService = lectureService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResultDto> list(){
        return orderService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto create(
            @RequestBody OrderDto orderDto
    ){
        return orderService.create(orderDto);
    }
}
