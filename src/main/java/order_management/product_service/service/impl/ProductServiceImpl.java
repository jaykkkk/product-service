package order_management.product_service.service.impl;


import order_management.product_service.dto.ProductRequestDTO;
import order_management.product_service.dto.ProductResponseDTO;
import order_management.product_service.models.Product;
import order_management.product_service.repo.ProductRepository;
import order_management.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Long createProduct(ProductRequestDTO productRequestDTO) {

        Product product= Product.builder().
                name(productRequestDTO.getName()).
                description(productRequestDTO.getDescription()).
                price(productRequestDTO.getPrice()).
                availableQuantity(productRequestDTO.getAvailableQuantity()).
                build();
        Product productSaved = productRepository.save(product);
        return productSaved.getId();
    }

    @CachePut(value = "products", key = "#id")
    @Override
    public void updateProduct(Long id, ProductRequestDTO productRequestDTO) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productRequestDTO.getName());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setAvailableQuantity(productRequestDTO.getAvailableQuantity());
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public ProductResponseDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .availableQuantity(product.getAvailableQuantity())
                    .build();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);

    }
}
