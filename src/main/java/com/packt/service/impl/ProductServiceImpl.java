package com.packt.service.impl;

import com.packt.domain.Product;
import com.packt.domain.repository.ProductRepository;
import com.packt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.getAllProducts();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public Product getProductById(String productId) {
        return productRepository.getProductById(productId);
    }

    public List<Product> getProductsByManufacturer(String manufacturer){
        return productRepository.getProductsByManufacturer(manufacturer);
    }

    public List<Product> getProductsByPrice(String low, String high) {
        return productRepository.getProductsByPrice(low, high);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
}
