package order_management.product_service.controller;


import order_management.product_service.dto.ProductRequestDTO;
import order_management.product_service.dto.ProductResponseDTO;
import order_management.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint methods would go here (e.g., createProduct, getProductById, etc.)
    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {

        return ResponseEntity.status(201).body(productService.createProduct(productRequestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok("Product updated successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id) {
        // Implementation for fetching product by ID would go here

        return ResponseEntity.ok(productService.getProductById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        // Implementation for deleting product by ID would go here
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }




}
