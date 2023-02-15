//package megatera.makaoGymbackEnd.services;
//
//import java.util.List;
//
//import megatera.makaoGymbackEnd.dtos.OrderDto;
//import megatera.makaoGymbackEnd.models.Order;
//import megatera.makaoGymbackEnd.repositories.OrderRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//
//class OrderServiceTest {
//    private OrderService orderService;
//    private ProductService productService;
//    private OptionService optionService;
//    private PtTicketService ptTicketService;
//
//    private OrderRepository orderRepository;
//
//    @BeforeEach
//    void setup() {
//        orderRepository = mock(OrderRepository.class);
//        orderService = new OrderService(orderRepository, productService, ptOptionService, ptTicketService);
//    }
//
//    @Test
//    void create() {
//        Long productId = 1L;
//        String ptStartDate = "2022/12/06";
//
////        OrderRegisterDto orderRegisterDto = new OrderRegisterDto(productId,1L,new OptionResultDto());
//
////        OrderResultDto orderResultDto = orderService.create(orderRegisterDto);
//
////        assertThat(orderResultDto.getPtStartDate()).isEqualTo("2022/12/06");
//    }
//
//    @Test
//    void list() {
//        given(orderRepository.findAll()).willReturn(List.of(
//                Order.fake(1L),
//                Order.fake(2L),
//                Order.fake(3L),
//                Order.fake(4L),
//                Order.fake(5L)
//        ));
//
//        List<OrderDto> orderDtos = orderService.list(userId);
//
//        assertThat(orderDtos).hasSize(5);
//    }
//}
