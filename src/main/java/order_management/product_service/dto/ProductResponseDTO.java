package order_management.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private Integer availableQuantity;
}
