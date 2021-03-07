package me.saukin.springRestApi.model;

import java.util.Objects;

/**
 *
 * @author saukin
 */
public class Product {
    
    private long prodId;
    private String prodName;
    private int prodPrice;

    
    private Product() {
    }
       
    
    public Product(long prodId, String prodName, int prodPrice) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.prodId != other.prodId) {
            return false;
        }
        if (this.prodPrice != other.prodPrice) {
            return false;
        }
        if (!Objects.equals(this.prodName, other.prodName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice + '}';
    }
    
    
}
