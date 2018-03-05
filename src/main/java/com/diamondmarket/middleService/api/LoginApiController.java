package com.diamondmarket.middleService.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.diamondmarket.middleService.model.Error;
import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.TransactionContext;
import com.diamondmarket.middleService.model.User;
import com.diamondmarket.middleService.service.LoginService;

import io.swagger.annotations.ApiParam;

@Controller
public class LoginApiController implements LoginApi{
	
	@Autowired
	private LoginService loginService;
	
	private TransactionContext createTransactionContext(HttpHeaders httpHeaders) {

		TransactionContext context = new TransactionContext();
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("MiddleLogin");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("MiddleLogin");
		}

		return context;
	}

	private HttpHeaders setHeaders(TransactionContext context) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("correlationId", context.getCorrelationId());
		headers.add("ApplicationLabel", context.getApplicationLabel());
		headers.add("Content-Type", "application/json");
		return headers;
	}

	private ResponseEntity<Response> successResponse(TransactionContext context, Object object, HttpStatus httpStatus) {
		HttpHeaders headers = setHeaders(context);
		Response response = new Response();
		response.setData(object);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}

	private ResponseEntity<Response> errorResponse(TransactionContext context, Exception exception,
			HttpStatus httpStatus) {
		HttpHeaders headers = setHeaders(context);
		Error error = new Error();
		error.setCode(httpStatus.toString() + "0001");
		error.setReason(exception.getMessage());
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response, headers, httpStatus);
		return responseEntity;
	}

	
	
	@Override
	public ResponseEntity<Response> createUser(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "Created user object", required = true) @Valid @RequestBody User user){
		TransactionContext context = createTransactionContext(httpHeaders);
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("MiddleLogin");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("MiddleLogin");
		}
		try {
			System.out.println("user :"+user.toString());
			return loginService.createUser(httpHeaders, user);

		} catch (Exception e) {
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<Response> loginUser(@RequestHeader HttpHeaders httpHeaders,
			@NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "username", required = true) String userName,
			@NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password){
		TransactionContext context = createTransactionContext(httpHeaders);
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("MiddleLogin");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("MiddleLogin");
		}
		try {

//			User loginUser = userService.loginUser(userName, password);

//			response = successResponse(context, loginUser, HttpStatus.OK);
			ResponseEntity<Response> loginUser = loginService.loginUser(httpHeaders,userName,password);
			System.out.println(loginUser.toString());
			return loginUser;

		} catch (Exception e) {
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<Response> deleteUser(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "The user that needs to be deleted", required = true) @RequestParam(value="userId", required=true) String userId){
		
		TransactionContext context = new TransactionContext();
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("MiddleLogin");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("MiddleLogin");
		}

		
		try {
			ResponseEntity<Response> deleteUser = loginService.deleteUser(httpHeaders,userId);
			System.out.println(deleteUser.toString());
			return deleteUser;
		}
		catch(Exception e) {
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Response> getUserById(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "", required = true) @RequestParam(value="userId",required=true) String userId) {

		
		TransactionContext context = createTransactionContext(httpHeaders);
		if (httpHeaders.get("correlationId") != null) {
			context.setCorrelationId(httpHeaders.get("correlationId").toString());
		} else {
			context.setCorrelationId("demo");
		}
		if (httpHeaders.get("ApplicationLabel") != null) {
			context.setApplicationLabel(httpHeaders.get("ApplicationLabel").toString());
		} else {
			context.setApplicationLabel("demo");
		}
		try {

			return loginService.getUserById(httpHeaders,userId);

		} catch (Exception e) {
			return errorResponse(context, e, HttpStatus.BAD_REQUEST);
	
		}

	}

}
