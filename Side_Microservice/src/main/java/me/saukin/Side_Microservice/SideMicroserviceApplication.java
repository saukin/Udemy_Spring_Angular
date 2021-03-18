package me.saukin.Side_Microservice;

import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SideMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SideMicroserviceApplication.class, args);
	}
    
//    @Bean
//    public RestTemplate restTemplate() {
//        
//        HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        
//        clientRequestFactory.setConnectTimeout(2300);
//        clientRequestFactory.setReadTimeout(2300);
//        
//        return new RestTemplate(clientRequestFactory);
//    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }
    
}
