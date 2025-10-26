package order_management.product_service.service;


import order_management.product_service.dto.ProductRequestDTO;
import order_management.product_service.dto.ProductResponseDTO;

public interface ProductService {

    Long createProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(Long id, ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(Long id);

    void deleteProductById(Long id);
}
