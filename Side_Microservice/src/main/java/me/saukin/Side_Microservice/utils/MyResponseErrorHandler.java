/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.Side_Microservice.utils;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

/**
 *
 * @author saukin
 */
@Component
public class MyResponseErrorHandler implements ResponseErrorHandler{

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Has Error");
        if (response.getStatusCode() != HttpStatus.OK) {
            System.out.println("Status code : " + response.getStatusCode());
            System.out.println("Response : " + response.getStatusText());
        
            return true;
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Error handled");
    }
    
}
