package com.learning.users.repository;

import com.learning.users.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {

	public Map<Integer, User> storage = new HashMap<>();

	@Override
	public void create(User user) {

		int idUser = storage.size() + 1;
		user.setId(idUser);
		storage.put(idUser, user);

	}

	@Override
	public User read(int idUser){

		if(storage.containsKey(idUser)){
			User user;
			user = storage.get(idUser);
			return user;
		}else{
			return null;
		}
	}

	@Override
	public User update(User userParam){

		storage.replace(userParam.getId(), userParam);
		return storage.get(userParam.getId());

	}

	@Override
	public boolean delete(int idUser){

		if(storage.containsKey(idUser)){
			storage.remove(idUser);
			return true;
		}
		this.count();
		return false;
	}

	@Override
	public int count() {
		return storage.size();
	}

}
