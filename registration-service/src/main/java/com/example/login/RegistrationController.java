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
		public String registerUser(
				@PathVariable("userName") String userName,
				@PathVariable("email") String email, 
				@PathVariable("password") String password
				) {
			
			User newUser = new User();
			newUser.setUsername(userName);
			
			//Hash the password before saving *the hash* in the database:
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			MessageDigest md;
			byte[] hashedPassword = password.getBytes();
			System.out.println("hp:"+hashedPassword);
			try {
				System.out.println("hpm");
				md = MessageDigest.getInstance("SHA-512");
				md.update(salt);
				hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
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
