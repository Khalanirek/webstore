package com.packt.controller;

import com.packt.domain.Product;
import com.packt.domain.repository.ProductRepository;
import com.packt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/products")
    public String list(Model model){

        model.addAttribute("products", productService.getAllProducts());

        return "products";
    }
}
