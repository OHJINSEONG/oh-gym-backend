package megatera.makaoGymbackEnd.services;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.ProductsDto;
import megatera.makaoGymbackEnd.models.Option;
import megatera.makaoGymbackEnd.models.Product;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsDto list() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream().map(Product::toDto).toList();

        return new ProductsDto(productDtos);
    }

    public ProductDetailDto find(Long id, List<OptionDto> optionDtos) {
        Product product = productRepository.getReferenceById(id);

        return product.toDetailDto(optionDtos);
    }

    public ProductDto create(String title, Long trainerId) {
        Product product = new Product(title, trainerId);

        productRepository.save(product);

        return product.toDto();
    }
}
