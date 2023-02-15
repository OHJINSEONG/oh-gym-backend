package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void toDetailDto() {
        List<Option> options = List.of(
                Option.fake(1L),
                Option.fake(1L),
                Option.fake(1L)
        );

        List<Trainer> trainers = List.of(
                Trainer.fake(new UserName("오진성"))
        );

        Product product = Product.fake(new Title("피티"));

        ProductDetailDto productDetailDto = product.toDetailDto(options,trainers);

        assertThat(productDetailDto.getOptions()).hasSize(3);
    }
}
