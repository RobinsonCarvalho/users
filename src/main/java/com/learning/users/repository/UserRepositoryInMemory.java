package com.learning.users.repository;

import java.util.HashMap;
import java.util.Map;

import com.learning.users.model.User;

public class UserRepositoryInMemory implements UserRepository {

	private Map<Integer, User> storage = new HashMap<>();

	@Override
	public void create(User user) {
		
		int idUser = storage.size() + 1;
		user.setId(idUser);
		storage.put(idUser, user);
	}

	@Override
	public int count() {
		return storage.size();
	}

}
