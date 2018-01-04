package com.packt.controller;

import com.packt.domain.Product;
import com.packt.domain.repository.ProductRepository;
import com.packt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("")
    public String list(Model model){

        model.addAttribute("products", productService.getAllProducts());

        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory){
        model.addAttribute("products", productService.getProductsByCategory(productCategory));

        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model){
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }



    //Metoda filtrujaca po kategorii, firmie i cenie, specjalnie użyte 3 typy wyciagania informacji z URL
    @RequestMapping("/{category}/{price}")
    public String filterProducts(@MatrixVariable(value="low", pathVar="price" )String low,
                                 @MatrixVariable(value="high", pathVar="price") String high,
                                 @PathVariable("category") String productCategory,
                                 @RequestParam("manufacturer") String productManufacturer,
                                 Model model){

        Set<Product> filtredProducts = new HashSet<Product>();

        filtredProducts.addAll(productService.getProductsByCategory(productCategory));
        filtredProducts.addAll(productService.getProductsByManufacturer(productManufacturer));
        filtredProducts.addAll(productService.getProductsByPrice(low, high));
        model.addAttribute("products", filtredProducts);

        return "products";
    }

}
