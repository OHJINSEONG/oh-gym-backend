package megatera.makaoGymbackEnd.controllers;

import megatera.makaoGymbackEnd.dtos.ProductDto;
import megatera.makaoGymbackEnd.dtos.ProductsDto;
import megatera.makaoGymbackEnd.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto products(){
        return productService.list();
    }

    @GetMapping("{id}")
    public ProductDto find(
            @PathVariable("id") Long id
    ){
        return productService.find(id);
    }
}
