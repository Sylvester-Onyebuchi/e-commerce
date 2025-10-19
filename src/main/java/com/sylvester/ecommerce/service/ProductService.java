package com.sylvester.ecommerce.service;

import com.sylvester.ecommerce.dto.ProductRequest;
import com.sylvester.ecommerce.dto.ProductResponse;
import com.sylvester.ecommerce.entity.Category;
import com.sylvester.ecommerce.entity.Product;
import com.sylvester.ecommerce.exception.ProductNotFoundException;
import com.sylvester.ecommerce.mapper.ProductMapper;
import com.sylvester.ecommerce.repository.CategoryRepository;
import com.sylvester.ecommerce.repository.ProductRepository;
import com.sylvester.ecommerce.exception.ProductAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDto).toList();
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        boolean exist = productRepository.existsByProductNameIgnoreCase(productRequest.getProductName());
        if (exist) {
            throw new ProductAlreadyExistException("Product already exist");
        }

        Category category = categoryRepository.findByNameIgnoreCase(productRequest.getCategoryName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(productRequest.getCategoryName());
                    return categoryRepository.save(newCategory);
                });

        Product product1 = productRepository.save(ProductMapper.toEntity(productRequest,category));
        return ProductMapper.toDto(product1);
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        return ProductMapper.toDto(product);
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setQuantity(productRequest.getQuantity());
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        productRepository.delete(product);
    }
}
