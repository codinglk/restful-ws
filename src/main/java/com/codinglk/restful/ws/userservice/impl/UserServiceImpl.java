package com.codinglk.restful.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.codinglk.restful.ws.shared.Utils;
import com.codinglk.restful.ws.ui.model.request.UserDetailsRequestModel;
import com.codinglk.restful.ws.ui.model.response.UserRest;
import com.codinglk.restful.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils)
	{
		this.utils =utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails, Map<String, UserRest> users) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);

		users.put(userId, returnValue);
		
		return returnValue;
		
	}

}
