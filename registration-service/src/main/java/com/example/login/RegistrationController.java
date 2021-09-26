package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.model.User;
import com.example.login.model.UserRepository;

@RestController
public class RegistrationController {

		@Autowired
		private UserRepository userRepository;
	
		@RequestMapping("/register-user/{userName}/{email}/{password}")
		public String registerUser(
				@PathVariable("userName") String userName,
				@PathVariable("email") String email, 
				@PathVariable("password") String password
				) {
			
			User newUser = new User();
			newUser.setUsername(userName);
			newUser.setPassword(password);
			newUser.setEmail(email);
			
			userRepository.save(newUser);
			
			
			return "Registration Success!";
		}

		@RequestMapping("/check")
		public String registerCheck() {
			return "Success!";
		}		
		
}
