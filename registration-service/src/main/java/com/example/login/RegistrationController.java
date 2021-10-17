package com.example.login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
		public Boolean registerUser(
				@PathVariable("userName") String userName,
				@PathVariable("email") String email, 
				@PathVariable("password") String password
				) {
			
			//Check if the user exists
			User validateUserExists = userRepository.findByUsername(userName);
			if (null!=validateUserExists) {
				return false;
			}
			
			User newUser = new User();
			newUser.setUsername(userName);
			
			//Hash code the password before saving *the hash* in the database:
			int hashedPassword = password.hashCode();
			
			newUser.setPassword(String.valueOf(hashedPassword));
			newUser.setEmail(email);
			
			userRepository.save(newUser);
			
			
			return true;
		}
		
		@RequestMapping("/check-exists/{userName}")
		public boolean checkExists( @PathVariable("userName") String userName) {
			User validateUserExists = userRepository.findByUsername(userName);
			if (null!=validateUserExists) {
				return false;
			}
			return true;
		}

		@RequestMapping("/delete-user/{userName}")
		public Boolean deleteUser(
				@PathVariable("userName") String userName
				) {
			
			//Check if the user exists
			User validateUserExists = userRepository.findByUsername(userName);
			if (null==validateUserExists) {
				return false;
			}
			
			userRepository.delete(validateUserExists);

			return true;
		}

		
		@RequestMapping("/check")
		public String registerCheck() {
			return "Success!";
		}		
		
}
