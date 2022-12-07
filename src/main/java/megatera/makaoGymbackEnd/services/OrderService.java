package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.OrderDto;
import megatera.makaoGymbackEnd.dtos.OrderResultDto;
import megatera.makaoGymbackEnd.models.Order;
import megatera.makaoGymbackEnd.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResultDto create(OrderDto orderDto) {
        Order order = new Order(orderDto.getPtStartDate(), orderDto.getProductId());

        orderRepository.save(order);

        return order.toDto();
    }

    public List<OrderResultDto> list() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(Order::toDto).toList();
    }
}
