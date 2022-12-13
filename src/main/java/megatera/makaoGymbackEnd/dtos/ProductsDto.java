package megatera.makaoGymbackEnd.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> productDtos;

    public ProductsDto() {
    }

    public ProductsDto(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }
}
