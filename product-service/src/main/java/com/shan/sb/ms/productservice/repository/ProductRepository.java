package com.shan.sb.ms.productservice.repository;

import com.shan.sb.ms.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
