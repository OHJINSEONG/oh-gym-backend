package megatera.makaoGymbackEnd.models;

import megatera.makaoGymbackEnd.dtos.ProductDto;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void toDto() {
        Product product = Product.fake("피티");

        ProductDto productDto = product.toDto();

        assertThat(productDto.getTitle()).isEqualTo("피티");
    }
}
