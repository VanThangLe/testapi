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

  Scenario Outline: Post a article
    When I post a article with <title> and <author> and <tags> and <content>

    Examples: 
      | title        | author  | tags      | content      |
      | Test article | ThangLV | Test tags | Test content |

  Scenario: Put a article
    When I put a article with title equals 'ArticleUpdate'

  Scenario: Get list articles
    When I get list articles

  Scenario: Get a article
    When I get a article

  Scenario: Delete a article
    When I delete a article
