package com.packt.domain.repository;

import com.packt.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByManufacturer(String manufacturer);
    List<Product> getProductsByPrice(String low, String high);
    void addProduct(Product product);
}
