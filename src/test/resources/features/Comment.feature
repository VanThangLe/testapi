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
	Scenario: Post a blog
		When I post a blog with name equal 'blog'
	
	Scenario: Post a article
		When I post a article with title equal 'title' and author equal 'author' and tags
  Scenario Outline: Post a comment
    When I post a comment <body> and <author> and <email> and <blogid> and <articleid>

    Examples: 
      | body    | author | email            | blogid | articleid |
      | comment | thang  | thanglv3@sapo.vn | blogid | articleid |

  Scenario: Put a comment
    When I put a comment with body equal 'comment update'

  Scenario: Get list comments
    When I get list comments

  Scenario: Get a comment
    When I get a comment

  Scenario: Delete comment
    When I delete comment
