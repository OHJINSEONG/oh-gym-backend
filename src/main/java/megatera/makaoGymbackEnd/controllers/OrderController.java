package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.OrderRegisterDto;
import megatera.makaoGymbackEnd.dtos.OrderResultDto;
import megatera.makaoGymbackEnd.models.KakaopayApprove;
import megatera.makaoGymbackEnd.services.KakaoPayService;
import megatera.makaoGymbackEnd.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final KakaoPayService kakaoPayService;

    public OrderController(OrderService orderService, KakaoPayService kakaoPayService) {
        this.orderService = orderService;
        this.kakaoPayService = kakaoPayService;
    }

    @GetMapping
    public List<OrderResultDto> list(
            @RequestAttribute("userId") Long userId
    ) {
        return orderService.list(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestBody OrderRegisterDto orderRegisterDto,
            @RequestAttribute("userId") Long userId
    ) {
        return orderService.create(
                userId,
                orderRegisterDto.getProductId(),
                orderRegisterDto.getOptionId(),
                orderRegisterDto.getItemName(),
                orderRegisterDto.getTotalPrice(),
                orderRegisterDto.getConsumerAddress(),
                orderRegisterDto.getConsumerGender(),
                orderRegisterDto.getConsumerAddressDetail(),
                orderRegisterDto.getConsumerBirthDate(),
                orderRegisterDto.getConsumerPhoneNumber(),
                orderRegisterDto.getCunsumerName()
        );
    }

    @GetMapping("/kakaoPaySuccess")
    public KakaopayApprove orderResult(
            @RequestParam("pg_token") String pgToken
    ) {
        return kakaoPayService.kakaoPayInfo(pgToken);
    }
}
