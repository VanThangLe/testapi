#Author: thanglv3@sapo.vn
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
@daily
Feature: Test daily

  @tag1
  Scenario Outline: I post a comment
    When I post a comment with <body> = 'Test comment'
    And <author> = 'Le Van Thang'
    And <email> = 'thanglv3@sapo.vn'
    Then I click GuiBL
  
  @tag3
  Scenario Outline: I put a comment
    When:
    
    Example:
    
    
  @tag4
	Scenario Outline: I get a comment
	
	@tag5
  Scenario Outline: I delete comment