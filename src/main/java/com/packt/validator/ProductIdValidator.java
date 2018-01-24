package com.packt.validator;

import com.packt.domain.Product;
import com.packt.exceptions.ProductNotFoundException;
import com.packt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    @Autowired
    private ProductService productService;
    public void initialize(ProductId constraintAnnotation) {
// intentionally left blank; this is the place toinitialize the constraint annotation for any sensibledefault values.
    }
    public boolean isValid(String value,ConstraintValidatorContext context) {
        Product product;
        try {
            product = productService.getProductById(value);
        } catch (ProductNotFoundException e) {
            return true;
        }
        if(product!= null) {
            return false;
        }
        return true;
    }
}

