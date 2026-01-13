package com.sparkx.ecommerce_api.service.impl;

import com.sparkx.ecommerce_api.exception.ResourceNotFoundException;
import com.sparkx.ecommerce_api.exception.UnauthorizedException;
import com.sparkx.ecommerce_api.model.Product;
import com.sparkx.ecommerce_api.model.User;
import com.sparkx.ecommerce_api.model.dto.request.ProductRequest;
import com.sparkx.ecommerce_api.model.dto.response.ProductResponse;
import com.sparkx.ecommerce_api.model.enums.UserRole;
import com.sparkx.ecommerce_api.repository.ProductRepository;
import com.sparkx.ecommerce_api.service.inf.IProductService;
import com.sparkx.ecommerce_api.service.inf.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final IUserService userService;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return mapToProductResponse(productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with Id: "+id)));
    }

    @Override
    public List<ProductResponse> getProductByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice,maxPrice).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

    }

    @Override
    public String createProduct(ProductRequest request) {
        User currentUser = userService.getCurrentUser();
        if(currentUser.getRole() != UserRole.SELLER){
            throw new UnauthorizedException("Only sellers can create products!");
        }
        Product product = Product.builder()
                .title(request.getTitle())
                .brand(request.getBrand())
                .model(request.getModel())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .seller(currentUser)
                .build();
        productRepository.save(product);
        return "Product Created Successfully";

    }

    @Override
    @Transactional
    public String updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with Id: "+id));
        User currentUser = userService.getCurrentUser();
        if ((Objects.equals(product.getSeller().getId(), currentUser.getId())) && currentUser.getRole() != UserRole.SELLER) {
                throw new UnauthorizedException("Only sellers of this product or Admin can update!");
        }

        product.setTitle(request.getTitle());
        product.setBrand(request.getBrand());
        product.setModel(request.getModel());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());

        productRepository.save(product);
        return "Product Updated Successfully";
    }

    @Override
    @Transactional
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with Id: "+id));
        User currentUser = userService.getCurrentUser();
        if ((Objects.equals(product.getSeller().getId(), currentUser.getId())) && currentUser.getRole() != UserRole.SELLER) {
            throw new UnauthorizedException("Only sellers of this product or Admin can Delete!");
        }
        productRepository.delete(product);
        return "Product Deleted successfully!";
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .brand(product.getBrand())
                .model(product.getModel())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .sellerId(product.getSeller().getId())
                .sellerName(product.getSeller().getFirstName()+" "+product.getSeller().getLastName())
                .build();
    }
}
