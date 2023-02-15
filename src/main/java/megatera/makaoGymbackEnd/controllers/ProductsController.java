package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.ProductRegisterDto;
import megatera.makaoGymbackEnd.services.OptionService;
import megatera.makaoGymbackEnd.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    private final OptionService optionService;

    public ProductsController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @GetMapping
    public List<ProductDetailDto> products() {
        return productService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(
            @RequestBody ProductRegisterDto productRegisterDto
    ) {
        ProductDto productDto = productService.create(
                productRegisterDto.getTitle(),
                productRegisterDto.getTrainerId(),
                productRegisterDto.getType()
        );

        optionService.create(productDto.getId(), productRegisterDto.getOptions());

        return productDto;
    }

    @GetMapping("{id}")
    public ProductDetailDto find(
            @PathVariable("id") Long id
    ) {
        return productService.find(id);
    }
}
