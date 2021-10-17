package com.example.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/registration",
        plugin = {"pretty", "html:target/cucumber/registration"},
        extraGlue = "com.example.login")
public class RegistrationServiceIntegrationTest {

}
