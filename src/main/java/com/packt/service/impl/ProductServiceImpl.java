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
}
