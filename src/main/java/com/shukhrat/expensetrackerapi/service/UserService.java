package com.shukhrat.expensetrackerapi.service;

import com.shukhrat.expensetrackerapi.entity.User;
import com.shukhrat.expensetrackerapi.entity.UserModel;

public interface UserService {

	User createUser(UserModel user);
	User read();
	User update(User user);
	void delete();
	User getLoggedInUser();
}
