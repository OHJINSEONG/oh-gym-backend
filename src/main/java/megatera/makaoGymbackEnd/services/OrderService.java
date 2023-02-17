package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.*;
import megatera.makaoGymbackEnd.models.Order;
import megatera.makaoGymbackEnd.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OptionService optionService;
    private final TicketService ticketService;
    private final PtTicketService ptTicketService;
    private final KakaoPayService kakaopayService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        OptionService optionService,
                        TicketService ticketService,
                        PtTicketService ptTicketService,
                        KakaoPayService kakaopayService
    ) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.optionService = optionService;
        this.ticketService = ticketService;
        this.ptTicketService = ptTicketService;
        this.kakaopayService = kakaopayService;
    }

    public List<OrderResultDto> list(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        List<OrderResultDto> orderResultDtos = new ArrayList<>();

        for (Order order : orders) {
            ProductDetailDto productDetailDto = productService.find(order.productId());

            OptionResultDto optionResultDto = optionService.find(order.optionId());

            PtTicketDto ptTicketDto = ptTicketService.findByOrderId(order.id());

            orderResultDtos.add(new OrderResultDto(order.toDto(), productDetailDto, optionResultDto, ptTicketDto));
        }

        return orderResultDtos;
    }

    public List<AdminOrderResultDto> adminList() {
        List<Order> orders = orderRepository.findAll();

        List<AdminOrderResultDto> adminOrderResultDtos = new ArrayList<>();

        for (Order order : orders) {
            ProductDetailDto productDetailDto = productService.find(order.productId());

            OptionResultDto optionResultDto = optionService.find(order.optionId());

            String dateOfPurchase = order.createdAt().toLocalDate().toString();

            adminOrderResultDtos.add(new AdminOrderResultDto(order.id(), productDetailDto, optionResultDto
                    , dateOfPurchase, productDetailDto.getType()));
        }

        return adminOrderResultDtos;
    }

    public String create(
            Long userId,
            Long productId,
            Long optionId,
            String itemName,
            Long totalPrice,
            String consumerAddress,
            String consumerGender,
            String consumerAddressDetail,
            String consumerBirthDate,
            String consumerPhoneNumber,
            String cunsumerName,
            String type
    ) {
        Order order = new Order(userId, productId, optionId, itemName, totalPrice, consumerAddress, consumerGender,
                consumerAddressDetail, consumerBirthDate, consumerPhoneNumber, cunsumerName);

        ProductDetailDto productDetailDto = productService.find(productId);

        OptionResultDto optionResultDto = optionService.find(optionId);

        orderRepository.save(order);

        ticketService.create(userId, productDetailDto.getTrainerId(), productDetailDto.getId(), order.id(), optionResultDto.getDateOfUse(), optionResultDto.getPtTimes(), productDetailDto.getType());

        if (type.equals("Test")) {
            return "https://oh-gym.fly.dev/orders/success";
        }

        return kakaopayService.kakaoPayReady(itemName, totalPrice).getNext_redirect_pc_url();
    }
}
