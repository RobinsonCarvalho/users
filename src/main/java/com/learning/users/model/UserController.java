package com.learning.users.model;

import com.learning.users.repository.UserRepositoryInMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/list")
    public List<User> list(
            @RequestParam(value = "active") boolean active,
            @RequestParam(value = "limitToList") int limitToList,
            @RequestParam(value = "name", defaultValue = "") String name){
        return userRepositoryInMemory.list(active, limitToList, name);
    }

}
