package com.diamondmarket.middleService.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.User;

public interface LoginClient {

	ResponseEntity<Response> createUser(HttpHeaders httpHeaders, User user);

	ResponseEntity<Response> loginUser(HttpHeaders httpHeaders, String username, String password);

	ResponseEntity<Response> deleteUser(HttpHeaders httpHeaders, String userId);

	ResponseEntity<Response> getUserById(HttpHeaders httpHeaders, String userId);

}
