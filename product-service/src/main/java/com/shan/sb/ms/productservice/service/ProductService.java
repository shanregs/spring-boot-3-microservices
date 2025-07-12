package com.shan.sb.ms.productservice.service;

import com.shan.sb.ms.productservice.dto.ProductRequest;
import com.shan.sb.ms.productservice.dto.ProductResponse;
import com.shan.sb.ms.productservice.model.Product;
import com.shan.sb.ms.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getDescription(),
                p.getSkuCode(),p.getPrice()))
                .toList();
    }

    public ProductResponse createProduct(ProductRequest p) {
        Product product = Product.builder()
                        .name(p.name())
                .description(p.description())
                .price(p.price())
                .skuCode(p.skuCode())
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getSkuCode(),
                product.getPrice());
    }
}

