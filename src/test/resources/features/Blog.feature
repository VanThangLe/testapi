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

  Scenario Outline: Post a blog
    When I post a blog with <name>

    Examples: 
      | name    |
      | Bong da |

  Scenario Outline: Put a blog
    When I put a blog with <name>

    Examples: 
      | name   |
      | Tintuc |

  Scenario: Get list blog
    When I get list blog

  Scenario: Get a blog
    When I get a blog

  Scenario: I delete a blog
    When I delete a blog
