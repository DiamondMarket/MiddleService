package com.diamondmarket.middleService.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.User;

public interface LoginService {

	ResponseEntity<Response> createUser(HttpHeaders httpHeaders, User user) throws Exception;

	ResponseEntity<Response> loginUser(HttpHeaders httpHeaders, String username, String password) throws Exception;

	ResponseEntity<Response> deleteUser(HttpHeaders httpHeaders, String userId) throws Exception;

	ResponseEntity<Response> getUserById(HttpHeaders httpHeaders, String userId);

}
