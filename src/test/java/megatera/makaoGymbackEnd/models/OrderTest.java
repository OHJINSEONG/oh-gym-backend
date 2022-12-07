package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.OrderResultDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void toDto() {
        Long productId = 1L;

        Order order = Order.fake(productId);

        OrderResultDto orderResultDto = order.toDto();

        assertThat(orderResultDto.getProductId()).isEqualTo(1L);
        assertThat(orderResultDto.getPtStartDate()).isEqualTo("2022/12/06");
    }
}
