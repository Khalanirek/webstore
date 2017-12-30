package com.packt.service.impl;

import com.packt.domain.Product;
import com.packt.domain.repository.ProductRepository;
import com.packt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductRepository productRepository;

    public void processOrder(String productId, int quantity) {

        Product productById = productRepository.getProductById(productId);

        if (productById.getUnitsInStock() < quantity){
            throw new IllegalArgumentException("Out of Stock. Available Units in stock" + productById.getUnitsInStock());
        }

        productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
    }
}
