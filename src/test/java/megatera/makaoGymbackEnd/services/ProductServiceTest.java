package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.ProductsDto;
import megatera.makaoGymbackEnd.models.Product;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void list() {
        given(productRepository.findAll()).willReturn(List.of(
                Product.fake("피티"),
                Product.fake("이용권")
        ));

        ProductsDto productsDto = productService.list();

        assertThat(productsDto.getProductDtos()).hasSize(2);
    }

    @Test
    void find() {
        given(productRepository.getReferenceById(any())).willReturn(Product.fake("피티"));

        ProductDto productDto = productService.find(1L);

        assertThat(productDto.getTitle()).isEqualTo("피티");
    }
}