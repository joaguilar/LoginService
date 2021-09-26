package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.login.model.User;
import com.example.login.model.UserRepository;

@RestController
public class LoginLogicController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/validate-login/{userName}/{password}")
	public Boolean validateLogin(
			@PathVariable("userName") String userName,
			@PathVariable("password") String password, Model model) {
		
		User loginUser=null;
		try {
			loginUser = userRepository.findByUsername(userName);
		} catch (Exception e) {
			System.out.println(String.format("User %s not found",userName));
		}
		if (null != loginUser) {
			// Get the hashed code password:

			String hashedPassword = String.valueOf(password.hashCode()); 

			if ((null!=loginUser.getPassword()) &&
					loginUser.getPassword().equals(hashedPassword)){
				model.addAttribute("UserName", userName);
				model.addAttribute("password", hashedPassword);
				return true;
			}
			model.addAttribute("errorMessage", "Incorrect Password");
			return false;
		}
		model.addAttribute("errorMessage", String.format("User %s not found",userName));
		return false;

	}
}
