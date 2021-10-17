package com.example.login;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class RegistrationHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String REGISTER_ENDPOINT = "/register-user";
    private final String CHECK_ENDPOINT = "/check-exists";
    private final String DELETE_ENDPOINT = "/delete-user";

    @LocalServerPort
    private int port = 8089;
    private final RestTemplate restTemplate = new RestTemplate();
    
    private String registrationEndpoint() {
        return SERVER_URL + ":" + port + REGISTER_ENDPOINT;
    }

    private String checkEndpoint() {
        return SERVER_URL + ":" + port + CHECK_ENDPOINT;
    }

    private String deleteEndpoint() {
        return SERVER_URL + ":" + port + DELETE_ENDPOINT;
    }

    public int register(final String user, final String email, final String password) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<?> httpEntity = new HttpEntity<>(headers); 
    	
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("userName", user);
    	params.put("email", email);
    	params.put("password", password);
    	
    	ResponseEntity<String> response =  restTemplate.exchange(
    			registrationEndpoint()+"/{userName}/{email}/{password}",
    			HttpMethod.GET,
    			httpEntity,
    			String.class,
    			params
    			);
    	 
    	 
    	 return response.getStatusCodeValue();
    }
    
    public boolean checkExists(final String user) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<?> httpEntity = new HttpEntity<>(headers); 
    	
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("userName", user);

    	
    	ResponseEntity<Boolean> response =  restTemplate.exchange(
    			checkEndpoint()+"/{userName}",
    			HttpMethod.GET,
    			httpEntity,
    			Boolean.class,
    			params
    			);
    	 
    	 
    	 return response.getBody();
    	
    }

    public void clean() {
        restTemplate.delete(registrationEndpoint());
    }

	public int delete(String theUser) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<?> httpEntity = new HttpEntity<>(headers); 
    	
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("userName", theUser);
    	
    	ResponseEntity<String> response =  restTemplate.exchange(
    			deleteEndpoint()+"/{userName}",
    			HttpMethod.GET,
    			httpEntity,
    			String.class,
    			params
    			);
    	 
    	 
    	 return response.getStatusCodeValue();
	}
}
