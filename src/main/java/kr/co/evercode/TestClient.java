package kr.co.evercode;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class TestClient {
	static final Logger LOGGER = LoggerFactory.getLogger(TestClient.class);
	
	public static void main(String[] args) {
	    final String uri = "http://localhost:8080/hello.do";
	    
	    SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
	    BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory);
	    
	    RestTemplate restTemplate = new RestTemplate(bufferingClientHttpRequestFactory);
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/json");
        
	    Hello hello = new Hello();
	    hello.setAge(30);
	    hello.setName("Mr.Kim");
	    
	    HttpEntity<?> requestEntity = new HttpEntity<Object>(hello, requestHeaders);
	    
        ResponseEntity<World> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, World.class);
        World world = responseEntity.getBody();

        LOGGER.info("world.getAge() = " + world.getAge());
        LOGGER.info("world.getGreeting() = " + world.getGreeting());
	}

}
