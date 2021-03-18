
package me.saukin.Side_Microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import me.saukin.Side_Microservice.models.Product;
import me.saukin.Side_Microservice.utils.MyResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author saukin
 */
@RestController
@RequestMapping("/side")
public class Side_Service_Controller {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("")
    public ResponseEntity<String> getProducts() {
        //setting resttemplate for handling errors
        restTemplate.setErrorHandler(new MyResponseErrorHandler());
        String resourceUri = "http://localhost:8080/product/";
//        String resourceUri = "http://httpstat.us/404";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUri,
                String.class);
        
        return response;
    }
    
    @GetMapping(value = "/{id}")
    public String getProduct(@PathVariable("id") long id) throws JsonProcessingException {
        //setting resttemplate for handling errors
        restTemplate.setErrorHandler(new MyResponseErrorHandler());
        String resourceUri = "http://localhost:8080/product/";
        ResponseEntity<String> productRE = restTemplate.getForEntity(resourceUri + ("/" + id), String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode product = mapper.readTree(productRE.getBody());
        return product.toPrettyString();
    }
    
    @PostMapping(value = "/fromObject")
    public ResponseEntity<Product> postProductP(@RequestBody Product product) {
        
        String resourseUri = "http://localhost:8080/product/p";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourseUri)
//                .queryParam("id", product.getProdId())
//                .queryParam("name", product.getProdName())
//                .queryParam("price", product.getProdPrice());

        HttpEntity<Product> request = new HttpEntity<>(product);
        
        ResponseEntity<Product> response = restTemplate.exchange(resourseUri,
                HttpMethod.POST, request, Product.class);
                
        return response;
    }
    
    @PostMapping(value = "")
    public Map<String, Object> postProduct(@RequestParam(value = "id") long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "price") int price) {
        
        Product product = new Product(id, name, price);
        
        String resourseUri = "http://localhost:8080/product/";
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourseUri)
                .queryParam("id", id).queryParam("name", name).queryParam("price", price);
        
        Map<String, Object> response = restTemplate.postForObject(builder.toUriString(),
                HttpEntity.EMPTY, Map.class);
                
        
        return response;
    }
    
    
    @PutMapping(value = "")
    public ResponseEntity<Product> putProductP(@RequestBody Product product) {
        
        String resourseUri = "http://localhost:8080/product/";
        HttpEntity<Product> request = new HttpEntity<>(product);
        
        ResponseEntity<Product> response = restTemplate.exchange(resourseUri,
                HttpMethod.PUT, request, Product.class);
                
        return response;
    }
    
    
    @DeleteMapping(value = "")
    public ResponseEntity<Map> deleteProductReqParam(@RequestParam(value = "id") long id) {
        
        String resourceUrl = "http://localhost:8080/product";
         UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("id", id);
        ResponseEntity<Map> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.DELETE, HttpEntity.EMPTY, Map.class);
        
        return response;
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map> deleteProductPVariable(@PathVariable("id") long id) {
        
        String resourceUrl = "http://localhost:8080/product/" + id;
//        HttpEntity<Long> request = new HttpEntity<>(id);
        
        ResponseEntity<Map> response = restTemplate.exchange(resourceUrl,
                HttpMethod.DELETE, HttpEntity.EMPTY, Map.class);
                
        return response;
    }
    
}
