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
		User loginUser=null;
		try {
			loginUser = userRepository.findByUsername(userName);
		} catch (Exception e) {
			System.out.println(String.format("User %s not found",userName));
		}
		if (null != loginUser) {
			// Get the hashed password:
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			MessageDigest md;
			byte[] hashedPassword = null;
			try {
				md = MessageDigest.getInstance("SHA-512");
				md.update(salt);
				hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((null!=loginUser.getPassword()) &&
					loginUser.getPassword().equals(hashedPassword)){
				model.addAttribute("UserName", userName);
				model.addAttribute("password", hashedPassword);
				return "homePage";
			}
			model.addAttribute("errorMessage", "Incorrect Password");
			return "loginPage";
		}
		model.addAttribute("errorMessage", String.format("User %s not found",userName));
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
		
		restTemplate.getForObject(
				String.format("http://localhost:8089/register-user/%s/%s/%s",userName,email1,password1), 
				String.class);
		model.addAttribute("registerSuccess","Successfully Registered user");
		model.addAttribute("errorMessage","");
		return "loginPage";
	}
		

}
