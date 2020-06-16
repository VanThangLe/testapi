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
  Scenario Outline: Post a comment
    When I post a comment with <body> and <author> and <email>

    Examples: 
      | body         | author       | email            |
      | Test comment | Le Van Thang | thanglv3@sapo.vn |

  @tag2
  Scenario Outline: Put a comment
    When I put a comment with <body> and <author> and <email>

    Examples: 
      | body                | author  | email                 |
      | Test comment update | ThangLV | thang.le.fc@gmail.com |

  @tag3
  Scenario: Get list comments
    When I get list comments

  @tag4
  Scenario: Get a comment
    When I get a comment

  @tag5
  Scenario: Delete comment
    When I delete comment
