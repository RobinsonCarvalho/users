package com.learning.users.repository;

import com.learning.users.model.Person;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {

	private Map<Integer, Person> storage = new HashMap<>();

	@Override
	public void create(Person user) {
		
		int idUser = storage.size() + 1;
		user.setId(idUser);
		storage.put(idUser, user);
	}

	@Override
	public int count() {
		return storage.size();
	}

}
