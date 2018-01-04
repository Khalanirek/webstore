package com.packt.service;

import com.packt.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product getProductById(String productId);

    List<Product> getProductsByManufacturer(String manufacturer);

    List<Product> getProductsByPrice(String low, String high);
}
