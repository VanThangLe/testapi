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
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcome
    And check more outcomes

  @tag2
  Scenario Outline: I put a comment
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
      
  @tag3
  Scenario Outline: I get list comment
  	Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step
  
  @tag4
  Scenario Outline: I get a comment
  	Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step
    
  @tag5
	Scenario Outline: I delete a comment
		Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step