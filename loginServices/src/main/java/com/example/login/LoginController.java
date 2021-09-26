package com.example.login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.login.model.User;
import com.example.login.model.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	 
	private RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping("/")
	public String checkMVC() {
		return "loginPage";
	}
	
	@RequestMapping("/login")
	public String loginHomePage(@RequestParam("userName") String userName,
			@RequestParam("password") String password, Model model) {

		boolean result = restTemplate.getForObject(
				String.format("http://localhost:8087/validate-login/%s/%s",userName,password), 
				Boolean.class);
		
		if (result) {
			model.addAttribute("UserName", userName);
			model.addAttribute("password", password);
			return "homePage";
		} 
		model.addAttribute("errorMessage", "Incorrect Username or Password");
		return "loginPage";
	}
	
	@RequestMapping("/register")
	public String goToRegistrationPage() {
		return "register";
	}
	
	@RequestMapping("/set-user")
	public String gotoRegisterMicroservice(@RequestParam("userName") String userName,@RequestParam("email1") String email1,
			@RequestParam("email2") String email2,
			@RequestParam("password1") String password1,
			@RequestParam("password2") String password2, Model model) {
		//Code to go to the registration microservice
		
		if (!password1.equals(password2)) {
			model.addAttribute("errorMessage", "Passwords don't match");
			return "register";
		} 
		if (!email1.equals(email2)) {
			model.addAttribute("errorMessage", "Emails don't match");
			return "register";
		} 
		
		boolean result = restTemplate.getForObject(
				String.format("http://localhost:8089/register-user/%s/%s/%s",userName,email1,password1), 
				Boolean.class);
		if (result) {
			model.addAttribute("registerSuccess","Successfully Registered user");
			return "loginPage";
		} else {
			model.addAttribute("errorMessage","User registration failed. Username already in use");
			return "register";
		}
		
	}
		

}
