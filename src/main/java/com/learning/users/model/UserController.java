package com.learning.users.model;

import com.learning.users.repository.UserRepositoryInMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserController {

    UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

    public static void main(String[] args){
        SpringApplication.run(UserController.class, args);
    }

    @PostMapping("/user/add")
    public void add(@RequestBody User user){
       userRepositoryInMemory.create(user);
    }

}
