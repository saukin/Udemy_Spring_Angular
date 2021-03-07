package me.saukin.springRestApi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.saukin.springRestApi.model.Product;
import me.saukin.springRestApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saukin
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping
    List<Product> getProducts() {
        return productService.getProducts();
    }
    
    @GetMapping("/{id}")
    Product getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }
    
    @PostMapping(value = "")
    public Map<String, Object> addProduct(@RequestParam(value = "id") Long id,
            @RequestParam(value = "name") String name, 
            @RequestParam(value = "price") Integer price) {
        
        productService.addProduct(id, name, price);
        
        Map<String, Object> map = new HashMap<>();
        map.put("status", "Product Added!");
        return map;
    }
    
//    @PutMapping(value = "")
//    public Map<String, Object> updateProduct(@RequestParam(value = "id") Long id,
//            @RequestParam(value = "name") String name, 
//            @RequestParam(value = "price") Integer price) {
//        
//        productService.updateProduct(id, name, price);
//        
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", "Product Updated!");
//        return map;
//    }
    
    @PutMapping(value = "") 
    public Product updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return product;
    }
    
    @DeleteMapping(value = "")
    public Product deleteProduct(@RequestParam(value = "id") long id) {
        return productService.removeProduct(id);
    }
    
}
