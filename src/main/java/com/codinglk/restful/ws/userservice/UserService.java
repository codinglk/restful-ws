package com.codinglk.restful.ws.userservice;


import com.codinglk.restful.ws.ui.model.request.UserDetailsRequestModel;
import com.codinglk.restful.ws.ui.model.response.UserRest;

import java.util.Map;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails, Map<String, UserRest> users);
}
