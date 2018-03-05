package com.diamondmarket.middleService.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginClientImpl implements LoginClient {

	@Override
	public ResponseEntity<Response> createUser(HttpHeaders httpHeaders, User user) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<User> request = new HttpEntity<User>(user, httpHeaders);
			return restTemplate.exchange(Constants.URL_USER, HttpMethod.POST, request, Response.class);
			
	    }
	    catch (HttpClientErrorException  | HttpServerErrorException httpClientOrServerExc) {
	    	//GSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				Response response = mapper.readValue(httpClientOrServerExc.getResponseBodyAsString(), Response.class);
				System.out.println(response.toString());

				httpClientOrServerExc.getResponseHeaders().remove("Connection");

				return new ResponseEntity<Response>(response, httpClientOrServerExc.getResponseHeaders(), httpClientOrServerExc.getStatusCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }
		
	
	}

	@Override
	public ResponseEntity<Response> loginUser(HttpHeaders httpHeaders, String userName, String password) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.URL_LOGIN_USER)
					// Add query parameter
					.queryParam("username", userName).queryParam("password", password);

			HttpEntity<User> request = new HttpEntity<User>(httpHeaders);
			ResponseEntity<Response> responseEntity = restTemplate.exchange(builder.buildAndExpand().toUri(),
					HttpMethod.GET, request, Response.class);
			return responseEntity;
		} 	    
		catch (HttpClientErrorException  | HttpServerErrorException httpClientOrServerExc) {
	    	//GSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				Response response = mapper.readValue(httpClientOrServerExc.getResponseBodyAsString(), Response.class);
//				System.out.println(response.toString());
//				System.out.println(response.getData());
//				System.out.println(response.getTimeStamp());
//				System.out.println(response.getError());
//				System.out.println(httpClientOrServerExc.getResponseHeaders());
				httpClientOrServerExc.getResponseHeaders().remove("Connection");
				
				return new ResponseEntity<Response>(response, httpClientOrServerExc.getResponseHeaders(), httpClientOrServerExc.getStatusCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }
		
		
	}

	@Override
	public ResponseEntity<Response> deleteUser(HttpHeaders httpHeaders, String userId) throws RestClientException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.URL_USER)
			        // Add query parameter
			        .queryParam("userId", userId);
	
		    HttpEntity<User> request = new HttpEntity<User>(httpHeaders);
		    System.out.println("Inside loginclient delete: ");
		    ResponseEntity< Response> response= restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.DELETE, request,Response.class);
		    System.out.println(response.getBody().toString());
		    return response;
	    }
	    catch (HttpClientErrorException  | HttpServerErrorException httpClientOrServerExc) {
	    	//GSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				Response response = mapper.readValue(httpClientOrServerExc.getResponseBodyAsString(), Response.class);
				System.out.println(response.toString());

				httpClientOrServerExc.getResponseHeaders().remove("Connection");

				return new ResponseEntity<Response>(response, httpClientOrServerExc.getResponseHeaders(), httpClientOrServerExc.getStatusCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }
		
	}

	@Override
	public ResponseEntity<Response> getUserById(HttpHeaders httpHeaders, String userId) {
		// TODO Auto-generated method stub
		try {
			RestTemplate restTemplate = new RestTemplate();
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.URL_USER)
			        // Add query parameter
			        .queryParam("userId", userId);
	
					
		    HttpEntity<User> request = new HttpEntity<User>(httpHeaders);
		    System.out.println("Inside loginclient get by id: ");
		    ResponseEntity< Response> response= restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, request,Response.class);
		    System.out.println(response.getBody().toString());
		    return response;
	    }
	    catch (HttpClientErrorException  | HttpServerErrorException httpClientOrServerExc) {
	    	//GSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				Response response = mapper.readValue(httpClientOrServerExc.getResponseBodyAsString(), Response.class);
				System.out.println(response.toString());

				httpClientOrServerExc.getResponseHeaders().remove("Connection");

				return new ResponseEntity<Response>(response, httpClientOrServerExc.getResponseHeaders(), httpClientOrServerExc.getStatusCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }
	}
	
}
