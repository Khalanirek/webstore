package com.packt.controller;

import com.packt.domain.Product;
import com.packt.domain.repository.ProductRepository;
import com.packt.exceptions.NoProductsFoundUnderCategoryException;
import com.packt.exceptions.ProductNotFoundException;
import com.packt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

        List<Product> products = productService.getProductsByCategory(productCategory);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model){
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct")
                                           Product newProduct, BindingResult result,
                                           HttpServletRequest request){
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = newProduct.getProductImage();
        String rootDirectory
                = request.getSession().getServletContext().getRealPath("/");
        if (productImage!=null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory+"WEB-INF\\classes\\images\\"+newProduct.getProductId() + ".jpg"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed",e);
            }
        }

        MultipartFile productPDF = newProduct.getProductPDF();
        if (productPDF != null && !productPDF.isEmpty())
            try{
                productPDF.transferTo((new File(rootDirectory + "WEB-INF\\classes\\pdf\\" + newProduct.getProductId() + ".pdf")));
            } catch (Exception e) {
                throw new RuntimeException("Product PDF saving failed", e);
            }

        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("productId","name","unitPrice","description",
                "manufacturer","category","unitsInStock", "productImage", "productPDF", "language");

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest
                                            req,ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url",req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }

}
