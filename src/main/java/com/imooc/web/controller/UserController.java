package com.imooc.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.junit.validator.PublicClassValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.mysql.jdbc.Field;

@RestController
@RequestMapping("/user")
public class UserController {

		@GetMapping
		@JsonView(User.UserSimpleView.class)
		public List<User> query(@RequestParam String username) {
			List<User> users = new ArrayList<>();
			users.add(new User());
			users.add(new User());
			users.add(new User());
			return users;
		}
		
		@GetMapping("/{id:\\d+}")
		@JsonView(User.UserDetailView.class)
		public User getInfo(@PathVariable String id) {
			System.out.println("调用getInfo服务");
//			throw new UserNotExistException(id);
			User user = new User();
			user.setUsername("tom");
			return user;
		}
		
		@PostMapping
		public User create(@Valid @RequestBody User user) {
			System.out.println(user.getId());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getBirthday());
			user.setId("1");
			return user;
		}
		
		@PutMapping("/{id:\\d+}")
		public User update(@Valid @RequestBody User user, BindingResult errors) {
			if (errors.hasErrors()) {
				errors.getAllErrors().stream().forEach(error -> {
//					FieldError fieldError = (FieldError)error;
//					String message = fieldError.getField() + " " + error.getDefaultMessage();
					System.out.println(error.getDefaultMessage());
				});
				
			}
			System.out.println(user.getId());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getBirthday());
			user.setId("1");
			return user;
		}
		
		@DeleteMapping("/{id:\\d+}")
		public void delete(@PathVariable String id) {
			System.out.println(id);
		}
		
		
		
		
		
		
		
		
}
