/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.springRestApi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author saukin
 */
public class ProductsBase {

    private static List<Product> productBase;

    private ProductsBase() {
        productBase = new ArrayList();
        productBase.add(new Product(1L, "prodName", 12));
        productBase.add(new Product(2L, "prod", 10));
        productBase.add(new Product(3L, "Name", 112));
        productBase.add(new Product(4L, "product", 2));
        
    }
    
    public static class ProductsBaseHolder {
        public static final ProductsBase INSTANCE = new ProductsBase();
    }
    
    public static ProductsBase getInstance() {
        return ProductsBaseHolder.INSTANCE;
    }
    
    
    public List<Product> getProducts() {
        return productBase;
    }

    public Product getProduct(long id) {
        for (Product p : productBase) {
            if (p.getProdId() == id) {
                return p;
            }
        }
        return null;
    }

    public void addProduct(Product newProduct) {
        productBase.add(newProduct);
    }
    
    public void updateProduct(Product uProduct) {
        for (int i = 0; i < productBase.size(); i++) {
            if (productBase.get(i).getProdId() == uProduct.getProdId()) {
                productBase.set(i, uProduct);
                break;
            }
        }
    }
    
    public Product removeProduct(long id) {
        Product dProduct = new Product(id, "", 0);
        for (Product p : productBase) {
            if (p.getProdId() == id) {
                dProduct = p;
                productBase.remove(p);
                break;
            }
        }
        return dProduct;
    }

}
