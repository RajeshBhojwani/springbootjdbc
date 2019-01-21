package com.example.springbootjdbc.controller;

import java.util.List;

import com.example.springbootjdbc.dao.UserDaoImpl;
import com.example.springbootjdbc.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserDaoImpl userDao;
 
    @PostMapping("/users")
    public ResponseEntity<User> createUser() {
        User user   =   userDao.create(getUser());
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
       
    }
    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id ) {
        return userDao.findUserById(id);
    }

    private User getUser() {
		User user = new User();
		user.setAddress("Marathahalli, Bangalore");
		user.setEmail("rajesh.bhojwani@gmail.com");
		user.setName("Rajesh Bhojwani");
		return user;
	}

}