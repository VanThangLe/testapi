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
      | name |
      | blog |

  Scenario Outline: Post a article comment
    When I post a article comment with <title> and <author> and <tags> and <content>

    Examples: 
      | title | author | tags | content |
      | title | author | tags | content |

  Scenario Outline: Post a comment
    When I post a comment <body> and <author> and <email>

    Examples: 
      | body    | author | email            |
      | comment | thang  | thanglv3@sapo.vn |

  Scenario Outline: Put a comment
    When I put a comment with <body>
      | body          |
      | CommentUpdate |

  Scenario: Get list comments
    When I get list comments

  Scenario: Get a comment
    When I get a comment

  Scenario: Delete comment
    When I delete comment
