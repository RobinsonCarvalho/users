package com.learning.users.repository;

import com.learning.users.model.Person;

public interface UserRepository {

	void create(Person user);
	
	int count();
	
}
