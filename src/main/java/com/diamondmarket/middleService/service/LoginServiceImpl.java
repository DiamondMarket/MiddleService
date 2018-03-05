package com.diamondmarket.middleService.service;

import static org.mockito.Matchers.endsWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.diamondmarket.middleService.client.LoginClient;
import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.User;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginClient loginClient;
	
	@Override
	public ResponseEntity<Response> createUser(HttpHeaders httpHeaders, User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			ResponseEntity<Response>  entity = loginClient.createUser(httpHeaders, user);
			if(entity.getStatusCode().equals(HttpStatus.CREATED)) {
				System.out.println("created: " + entity.getBody().toString());
			}
			else {
				System.out.println("not created: " + entity.getBody().toString());
				
			}
			
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public ResponseEntity<Response> loginUser(HttpHeaders httpHeaders, String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		try {
			return loginClient.loginUser(httpHeaders, userName, password);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public ResponseEntity<Response> deleteUser(HttpHeaders httpHeaders, String userId) throws Exception {
		try {
			ResponseEntity<Response>  entity =loginClient.deleteUser(httpHeaders, userId);
			System.out.println(entity.getStatusCode().toString());
			System.out.println(entity.getBody().toString());
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}

	@Override
	public ResponseEntity<Response> getUserById(HttpHeaders httpHeaders, String userId) {
		// TODO Auto-generated method stub
		try {
			ResponseEntity<Response>  entity =loginClient.getUserById(httpHeaders,userId);
			System.out.println(entity.getStatusCode().toString());
			System.out.println(entity.getBody().toString());
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
