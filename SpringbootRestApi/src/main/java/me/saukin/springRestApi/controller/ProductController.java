package me.saukin.springRestApi.controller;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import me.saukin.springRestApi.model.Product;
import me.saukin.springRestApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saukin
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        CacheControl cacheControl = CacheControl.noCache();
        return ResponseEntity.ok().cacheControl(cacheControl).body(productService.getProducts());
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) throws Exception {
        Product prod = productService.getProduct(id);
        if (prod == null) {
            throw new Exception("Nothing found");
        }
        return prod;
    }
    
    @GetMapping(value = "temp")
    public ResponseEntity<String> getTemp() {
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        int temp = (int) (Math.random() * 50) + 10;
        String response = String.format("<h3>Current tempreture : %d Degrees<h3>"
                + "<h3>Response from server %s </h3>"
                + "<a href = ''>Once more</a>"
                , temp, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        
        return ResponseEntity.ok().cacheControl(cacheControl).body(response);
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
    
    @PostMapping(value = "/p")
    public Map<String, Object> addProductP(@RequestBody @Valid Product product) {
        
        productService.addProduct(product);
        
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
    public Product updateProduct(@RequestBody @Valid Product product) {
        productService.updateProduct(product);
        return product;
    }
    
    @PatchMapping(value = "/{id}")
    public @ResponseBody void patchProduct(@PathVariable long id, 
            @RequestBody Map<Object, Object> fields) {
        Product product = productService.getProduct(id);
        //Map : key - field , value - its value
        fields.forEach((key, value) -> {
            //use reflection to get field k of product and set its to value 
            Field field = ReflectionUtils.findField(Product.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, product, value);
        });
        productService.updateProduct(product);
    }
    
    
    @DeleteMapping(value = "")
    public Product deleteProduct(@RequestParam(value = "id") long id) {
        return productService.removeProduct(id);
    }
    
}
