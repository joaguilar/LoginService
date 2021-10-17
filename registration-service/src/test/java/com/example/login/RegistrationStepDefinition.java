package com.example.login;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RegistrationStepDefinition {
	
	
    private final Logger log = LoggerFactory.getLogger(RegistrationStepDefinition.class);

	@Autowired
	private RegistrationHttpClient registrationHttpClient;
	
	@When("^I register user \"(.+)\" with email \"(.+)\" and using \"(.+)\" as the password$")
	public void i_register_user_with_password(final String theUser, final String theEmail, final String thePassword) {
		log.info("Registering user {}, email {}, password {}",theUser, theEmail, thePassword);
		int statusCode = registrationHttpClient.register(theUser, theEmail, thePassword);
		assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
	}

	@When("^I delete user \"(.+)\"$")
	public void i_delete_user(final String theUser) {
		log.info("Deleting user {}",theUser);
		int statusCode = registrationHttpClient.delete(theUser);
		assertThat(statusCode).isEqualTo(HttpStatus.OK.value());
	}

    
	@Then("^the user \"(\\w+)\" shouldn't exists")
	public void the_user_shouldnt_exist(final String userName) {
		log.info("Check user {} exists",userName);
		boolean result = registrationHttpClient.checkExists(userName);
		assertThat(result == false);
	}
	
	@Then("^the user \"(\\w+)\" should exists")
	public void the_user_should_exist(final String userName) {
		log.info("Check user {} exists",userName);
		boolean result = registrationHttpClient.checkExists(userName);
		assertThat(result == true);
	}

}
