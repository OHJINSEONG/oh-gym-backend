package megatera.makaoGymbackEnd.services;

import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.OptionResultDto;
import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.models.*;
import megatera.makaoGymbackEnd.repositories.OptionRepository;
import megatera.makaoGymbackEnd.repositories.ProductRepository;
import megatera.makaoGymbackEnd.repositories.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final TrainerRepository trainerRepository;

    public ProductService(ProductRepository productRepository, OptionRepository optionRepository, TrainerRepository trainerRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<ProductDetailDto> list() {
        List<Product> products = productRepository.findAll();

        List<Trainer> trainers = trainerRepository.findAll();

        List<Option> options = optionRepository.findAll();

        return products.stream().map(product -> product.toDetailDto(options, trainers)).toList();
    }

    public ProductDetailDto find(Long productId) {
        Product product = productRepository.getReferenceById(productId);

        List<Trainer> trainers = trainerRepository.findAll();

        List<Option> options = optionRepository.findAll();

        return product.toDetailDto(options, trainers);
    }

    public ProductDto create(String title, Long trainerId, String type) {
        Product product = new Product(new Title(title), trainerId, new Category(type));

        productRepository.save(product);

        return product.toDto();
    }
}
