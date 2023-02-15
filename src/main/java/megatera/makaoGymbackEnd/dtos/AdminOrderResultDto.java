package megatera.makaoGymbackEnd.dtos;

public class AdminOrderResultDto {
    private Long id;
    private ProductDetailDto productImformation;
    private OptionResultDto option;
    private String dateOfPurchase;
    private String type;

    public AdminOrderResultDto(
            Long id,
            ProductDetailDto productImformation,
            OptionResultDto option,
            String dateOfPurchase,
            String type
    ) {
        this.id = id;
        this.productImformation = productImformation;
        this.option = option;
        this.dateOfPurchase = dateOfPurchase;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public ProductDetailDto getProductImformation() {
        return productImformation;
    }

    public OptionResultDto getOption() {
        return option;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getType() {
        return type;
    }
}
