package megatera.makaoGymbackEnd.controllers;

import java.util.List;
import megatera.makaoGymbackEnd.dtos.OptionDto;
import megatera.makaoGymbackEnd.dtos.ProductDetailDto;
import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.ProductRegisterDto;
import megatera.makaoGymbackEnd.dtos.ProductsDto;
import megatera.makaoGymbackEnd.services.OptionService;
import megatera.makaoGymbackEnd.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ProductsDto products(){
        return productService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(
            @RequestBody ProductRegisterDto productRegisterDto
    ){
        return productService.create(
                productRegisterDto.getTitle(),
                productRegisterDto.getTrainerId()
        );
    }

    @GetMapping("{id}")
    public ProductDetailDto find(
            @PathVariable("id") Long id
    ){
        List<OptionDto> optionDtos = optionService.list(id);

        return productService.find(id,optionDtos);
    }
}
