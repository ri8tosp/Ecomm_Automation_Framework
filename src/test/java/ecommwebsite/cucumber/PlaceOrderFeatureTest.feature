
@tag
Feature: Placing an order for a product on Ecommerce website 


  Background: 
  
  Given I am on Ecommerce page 

  @tag2
  Scenario Outline: This is a  +(ve) test case of purchasing the order
  
  
    Given User logs in with <username> and <password> and lands into the product page
    When I add the product <productName> to cart
    And at checkout page I validate that the <productName> is present and then submit the order
    Then I verify that "THANKYOU FOR THE ORDER." is displayed on ConfirmationPage

    Examples: 
      | username                        | password                | productName |
      | rahulexample@abc.com            | ABCD123@abc             | ZARA COAT 3 |
