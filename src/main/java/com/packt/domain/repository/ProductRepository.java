package com.packt.domain.repository;

import com.packt.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String productId);
}
