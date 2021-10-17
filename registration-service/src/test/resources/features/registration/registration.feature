#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Registration
Feature: Registration
  I want to use this template for my feature file

  @UserRegistration
  Scenario: Registering a user
    When I register user "<theUser>" with email "<theEmail>" and using "<thePassword>" as the password 
    Then the user "<theUser>" should exists
    
    Examples: 
      | theUser   | theEmail	       | thePassword  |
      | testUser7 | email7@login.com | password7    |
      | testUser5 | email5@login.com | password5    |
      | testUser4 | email4@login.com | password4    |
      | testUser3 | email3@login.com | password3    |
      | testUser2 | email2@login.com | password2    |
      
      
  @UserRegistration
  Scenario: Deleting a user
    When I delete user "<theUser>"
    Then the user "<theUser>" shouldn't exists


    Examples: 
      | theUser   | theEmail	       | thePassword  |
      | testUser7 | email7@login.com | password7    |
      | testUser5 | email5@login.com | password5    |
      | testUser4 | email4@login.com | password4    |
      | testUser3 | email3@login.com | password3    |
      | testUser2 | email2@login.com | password2    |

