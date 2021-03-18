package me.saukin.springRestApi.service;

import java.util.List;
import me.saukin.springRestApi.model.Product;
import me.saukin.springRestApi.model.ProductsBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author saukin
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    ProductsBase productBase = ProductsBase.getInstance();
    
    @Override
    public List<Product> getProducts() {
        List<Product> products = productBase.getProducts();
        
        return products;
    }

    @Override
    public Product getProduct(long id) {
        return productBase.getProduct(id);
    }
    
    @Override
    public void addProduct(long id, String name, int price) {
        Product newProduct = new Product(id, name, price);
        productBase.addProduct(newProduct);
    }
    
    @Override
    public void addProduct(Product product) {
        productBase.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productBase.updateProduct(product);
    }

    @Override
    public Product removeProduct(long id) {
        return productBase.removeProduct(id);
    }
    
       
}
