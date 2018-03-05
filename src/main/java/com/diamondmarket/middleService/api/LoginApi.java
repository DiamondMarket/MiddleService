package com.diamondmarket.middleService.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.diamondmarket.middleService.model.Response;
import com.diamondmarket.middleService.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-02-17T15:10:28.385Z")

@Api(value = "login", description = "login API")
public interface LoginApi {

	@ApiOperation(value = "Create user", notes = "", response = Response.class, tags = {
			"login", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "successful operation", response = Response.class),
			@ApiResponse(code = 400, message = "Invalid data", response = Response.class) })
	@ApiImplicitParams({ @ApiImplicitParam(name = "correlationId", value = "Correlation ID", paramType = "header"),
			@ApiImplicitParam(name = "ApplicationLabel", value = "Application label", paramType = "header") })

	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<Response> createUser(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "Created user object", required = true) @Valid @RequestBody User body);
	
	@ApiOperation(value = "Logs user into the system", notes = "", response = Response.class, tags = { "login", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Response.class),
			@ApiResponse(code = 400, message = "Invalid username/password supplied", response = Response.class) })
	@ApiImplicitParams({ @ApiImplicitParam(name = "correlationId", value = "Correlation ID", paramType = "header"),
			@ApiImplicitParam(name = "ApplicationLabel", value = "Application label", paramType = "header") })

	@RequestMapping(value = "/user/login", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Response> loginUser(@RequestHeader HttpHeaders httpHeaders,
			@NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "username", required = true) String username,
			@NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password);

	@ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = Response.class, tags = {
			"login", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User deleted successfully", response = Response.class),
			@ApiResponse(code = 400, message = "Invalid username supplied", response = Response.class),
			@ApiResponse(code = 404, message = "User not found", response = Response.class) })
	@ApiImplicitParams({ @ApiImplicitParam(name = "correlationId", value = "Correlation ID", paramType = "header"),
			@ApiImplicitParam(name = "ApplicationLabel", value = "Application label", paramType = "header") })
	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Response> deleteUser(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "The user that needs to be deleted", required = true) @RequestParam(value="userId", required=true) String userId);
	
	@ApiOperation(value = "Get user by userId", notes = "", response = Response.class, tags = { "login", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Response.class),
			@ApiResponse(code = 400, message = "Invalid userId supplied", response = Response.class),
			@ApiResponse(code = 404, message = "User not found", response = Response.class) })
	@ApiImplicitParams({ @ApiImplicitParam(name = "correlationId", value = "Correlation ID", paramType = "header"),
			@ApiImplicitParam(name = "ApplicationLabel", value = "Application label", paramType = "header") })

	@RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Response> getUserById(@RequestHeader HttpHeaders httpHeaders,
			@ApiParam(value = "", required = true) @RequestParam(value="userId",required=true) String userId);

}
