package me.saukin.springRestApi.service;

import java.util.List;
import me.saukin.springRestApi.model.Product;

/**
 *
 * @author saukin
 */
public interface ProductService {
    
    List<Product> getProducts();
    Product getProduct(long id);
    void addProduct(long id, String name, int price);
    void updateProduct(Product product);
    Product removeProduct(long id);
    
}
