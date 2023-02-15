package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.OptionRepository;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class ProductServiceTest {
    private ProductService productService;

    private ProductRepository productRepository;

    private OptionRepository optionRepository;

    private TrainerRepository trainerRepository;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        optionRepository = mock(OptionRepository.class);
        trainerRepository = mock(TrainerRepository.class);
        productService = new ProductService(productRepository, optionRepository, trainerRepository);
    }

    @Test
    void list() {
        given(productRepository.findAll()).willReturn(List.of(
                Product.fake(new Title("피티")),
                Product.fake(new Title("이용권"))
        ));

        List<ProductDetailDto> productDetailDtos = productService.list();

        assertThat(productDetailDtos).hasSize(2);
    }

    @Test
    void find() {
        Long productId = 1L;

        Product product = Product.fake(new Title("피티"));

        given(productRepository.getReferenceById(productId))
                .willReturn(product);

        given(trainerRepository.findAll()).willReturn(List.of(
                Trainer.fake(new UserName("오진성"))
        ));

        given(optionRepository.findAll()).willReturn(List.of(
                Option.fake(productId),
                Option.fake(productId)
        ));

        ProductDetailDto productDetailDto = productService.find(productId);

        assertThat(productDetailDto.getTitle()).isEqualTo("피티");
        assertThat(productDetailDto.getOptions()).hasSize(2);
    }
}
