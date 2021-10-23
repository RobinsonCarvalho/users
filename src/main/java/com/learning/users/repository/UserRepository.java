package com.learning.users.repository;

import com.learning.users.model.User;

public interface UserRepository {

	void create(User user);
	
	int count();
	
}
