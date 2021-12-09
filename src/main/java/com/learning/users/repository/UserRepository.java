package com.learning.users.repository;

import com.learning.users.model.User;

public interface UserRepository {

	void create(User user);

	User read(int id);

	User update(User user);

	boolean delete(int id);

	int count();
	
}
