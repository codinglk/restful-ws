package com.codinglk.restful.ws.ui.controllers;

import com.codinglk.restful.ws.exceptions.UserServiceException;
import com.codinglk.restful.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.codinglk.restful.ws.ui.model.request.UserDetailsRequestModel;
import com.codinglk.restful.ws.ui.model.response.UserRest;
import com.codinglk.restful.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

	static Map<String, UserRest> users = users = new HashMap<>();
	
	@Autowired
	UserService userService;

	/* @RequestParam example with defaultValue and required, Uncomment this code for testing and comment other getUsers method
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
	{
		return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}

	 */



	@GetMapping
	public Map<String, UserRest> getUsers()
	{
		/*Test java.lang.NullPointerException - Uncomment the code for testing, AppExceptionsHandler class >> handleSpecificExceptions method will be called.
		String nullPointerExceptionTest = null;
		if(nullPointerExceptionTest.length() < 10) System.out.println("This line will not be executed");

		 */


		/* Test java.lang.IndexOutOfBoundsException - Uncomment the code for testing, AppExceptionsHandler class >> handleAnyException method will be called.
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("Java");
		myList.add("Spring");
		myList.add("Hibernate");
		System.out.println(myList.get(5));

		 */


		/* Test UserServiceException - Uncomment the code for testing, AppExceptionsHandler class >> handleSpecificExceptions method will be called.
		boolean throwUserServiceException = true;
		if(throwUserServiceException) throw  new UserServiceException("UserServiceException is thrown from the code.");

		 */

		return users;
	}

	@GetMapping(path="/{userId}", 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					} )
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = userService.createUser(userDetails, users);
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}", consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		 UserRest storedUserDetails = users.get(userId);
		 storedUserDetails.setFirstName(userDetails.getFirstName());
		 storedUserDetails.setLastName(userDetails.getLastName());
		 
		 users.put(userId, storedUserDetails);
		 
		 return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id)
	{
		users.remove(id);
		
		return ResponseEntity.noContent().build();
	}
}
