
@tag
Feature: Error validation
  


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I am on Ecommerce page 
    And User logs in with <username> and <password> and lands into the product page
    Then "Incorrec email or password." should be displayed

     Examples: 
      | username                        | password                | productName |
      | rahulexample@abc.com            | ABCD123@a               | ZARA COAT 3 |
